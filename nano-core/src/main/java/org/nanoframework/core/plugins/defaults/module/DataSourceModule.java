/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nanoframework.core.plugins.defaults.module;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;

import org.nanoframework.commons.loader.PropertiesLoader;
import org.nanoframework.commons.util.ReflectUtils;
import org.nanoframework.core.plugins.Module;
import org.nanoframework.core.plugins.PluginLoaderException;

import com.google.common.collect.Lists;

/**
 * @author yanghe
 * @since 1.1
 */
public class DataSourceModule extends Module {
    private static final List<String> DATA_SOURCE_LOADER_CLASS_NAMES = Lists.newArrayList(
        "org.nanoframework.orm.jdbc.JdbcDataSourceLoader",
        "org.nanoframework.orm.mybatis.MybatisDataSourceLoader"
    );
    
    @Override
    public List<Module> load() throws Throwable {
        DATA_SOURCE_LOADER_CLASS_NAMES.forEach(clsName -> load(clsName));
        return modules;
    }
    
    @SuppressWarnings("unchecked")
    protected void load(final String clsName) {
        try {
            final Class<?> cls = Class.forName(clsName);
            final Object obj = ReflectUtils.newInstance(cls);
            PropertiesLoader.PROPERTIES.putAll((Map<String, Properties>) cls.getMethod("getLoadProperties").invoke(obj));
            modules.addAll((List<Module>) cls.getMethod("getModules").invoke(obj));
        } catch (final Throwable e) {
            if (!(e instanceof ClassNotFoundException)) {
                throw new PluginLoaderException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void config(final ServletConfig config) throws Throwable {

    }

    @Override
    protected void configure() {

    }

}
