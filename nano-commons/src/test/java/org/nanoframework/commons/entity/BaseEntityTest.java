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
package org.nanoframework.commons.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author yanghe
 * @date 2015年10月8日 下午2:11:35
 */
public class BaseEntityTest {

    @Test
    public void getAttributeNamesTest() {
        List<String> attrList = new ArrayList<String>() {
            private static final long serialVersionUID = 1L;
            {
                add("id");
                add("name");
            }
        };

        UseEntity entity = new UseEntity();
        final String[] attrs = entity._getAttributeNames();
        final String[] attrs2 = entity._getAttributeNames();
        assertEquals(attrs == attrs2, true);
        assertEquals(2, attrs.length);
        assertTrue(attrList.contains(attrs[0]));
        assertTrue(attrList.contains(attrs[1]));
    }

    @Test
    public void getAttributeValueTest() {
        UseEntity entity = new UseEntity();
        entity.setId("1234567890");
        assertEquals("1234567890", entity._getAttributeValue("id"));
        try {
            entity._getAttributeValue("");
        } catch (final Throwable e) {
            assertEquals(e instanceof IllegalArgumentException, true);
        }
    }

    @Test
    public void getAttributeValueByDefaultTest() {
        UseEntity entity = new UseEntity();
        assertEquals("1234567890", entity._getAttributeValue("id", "1234567890"));
    }

    @Test
    public void setAttributeValueTest() {
        UseEntity entity = new UseEntity();
        entity._setAttributeValue("id", "1234567890");
        assertEquals("1234567890", entity.getId());
    }

    @Test
    public void getBeanToMapTest() {
        UseEntity entity = new UseEntity();
        entity.setId("id0");
        entity.setName("name0");

        Map<String, Object> map = entity._getBeanToMap();
        assertNotNull(map);
        assertEquals("id0", map.get("id"));
        assertEquals("name0", map.get("name"));
    }

    @Test
    public void getMapToBeanTest() {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("id", "id0");
                put("name", "name0");
            }
        };

        UseEntity entity = UseEntity._getMapToBean(map, UseEntity.class);
        assertNotNull(entity);
        assertEquals("id0", entity.getId());
        assertEquals("name0", entity.getName());
    }

    @Test
    public void getMapToBeansTest() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>() {
            private static final long serialVersionUID = 1L;
            {
                add(new HashMap<String, Object>() {
                    private static final long serialVersionUID = 1L;
                    {
                        put("id", "id0");
                        put("name", "name0");
                    }
                });
            }
        };

        List<UseEntity> entityList = UseEntity._getMapToBeans(list, UseEntity.class);
        assertNotNull(entityList);
        assertEquals(1, entityList.size());
        assertEquals("id0", entityList.get(0).getId());
        assertEquals("name0", entityList.get(0).getName());
    }
}
