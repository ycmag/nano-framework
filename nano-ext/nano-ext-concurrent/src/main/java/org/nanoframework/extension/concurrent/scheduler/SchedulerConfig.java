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
package org.nanoframework.extension.concurrent.scheduler;

import java.util.concurrent.ThreadPoolExecutor;

import org.nanoframework.commons.entity.BaseEntity;
import org.nanoframework.extension.concurrent.exception.SchedulerException;

/**
 * 
 * @author yanghe
 * @since 1.3
 */
public class SchedulerConfig extends BaseEntity {
    private static final long serialVersionUID = 5426844475306359870L;
    private static final Integer ZERO_INT = Integer.valueOf(0);
    private static final Long ZERO_LONG = Long.valueOf(0);
    private String id;
    private String name;
    private String group;
    private ThreadPoolExecutor service;
    private Boolean beforeAfterOnly = Boolean.FALSE;
    private Integer runNumberOfTimes = ZERO_INT;
    private Long interval = ZERO_LONG;
    private Integer num = ZERO_INT;
    private Integer total = ZERO_INT;
    private CronExpression cron;
    private Boolean daemon = Boolean.FALSE;
    private Boolean lazy = Boolean.FALSE;
    private String[] defined = {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ThreadPoolExecutor getService() {
        return service;
    }

    public void setService(ThreadPoolExecutor service) {
        this.service = service;
    }

    public Boolean getBeforeAfterOnly() {
        return beforeAfterOnly;
    }

    public void setBeforeAfterOnly(Boolean beforeAfterOnly) {
        this.beforeAfterOnly = beforeAfterOnly;
    }

    public Integer getRunNumberOfTimes() {
        return runNumberOfTimes;
    }

    public void setRunNumberOfTimes(Integer runNumberOfTimes) {
        if (runNumberOfTimes == null || runNumberOfTimes < 0)
            throw new SchedulerException("运行次数不能小于0.");

        this.runNumberOfTimes = runNumberOfTimes;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CronExpression getCron() {
        return cron;
    }

    public void setCron(CronExpression cron) {
        this.cron = cron;
    }

    public Boolean getDaemon() {
        return daemon;
    }

    public void setDaemon(Boolean daemon) {
        this.daemon = daemon;
    }

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }

    public String[] getDefined() {
        return defined;
    }

    public void setDefined(String[] defined) {
        this.defined = defined;
    }

}
