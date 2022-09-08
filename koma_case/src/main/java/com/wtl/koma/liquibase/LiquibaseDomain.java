/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wtl.koma.liquibase;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public final class LiquibaseDomain {

    private static final Logger logger = LoggerFactory.getLogger(LiquibaseDomain.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @PostConstruct
    private void executeV1(){
        SpringLiquibase liquibase = new SpringLiquibase();
        // 用户模块Liquibase文件路径
        liquibase.setChangeLog("classpath:liquibase/changelogs/changelog_v0.1.xml");
        liquibase.setDataSource(jdbcTemplate.getDataSource());
        liquibase.setShouldRun(true);
        liquibase.setResourceLoader(new DefaultResourceLoader());
//        // 覆盖Liquibase changelog表名
//        liquibase.setDatabaseChangeLogTable("user_changelog_table");
//        liquibase.setDatabaseChangeLogLockTable("user_changelog_lock_table");
        try {
            liquibase.afterPropertiesSet();
        } catch (LiquibaseException e) {
            logger.error("执行liquibase异常", e);
        }
    }

}
