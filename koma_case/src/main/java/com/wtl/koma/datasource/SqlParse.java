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

package com.wtl.koma.datasource;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserFeature;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;

import java.util.List;

/**
 *
 */
public final class SqlParse {

    public static void main(String[] args) {
        String dbType = "oracle";
        String sql = "select 1 from (select * from a left join b on a.id = b..id) a";
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType, new SQLParserFeature[]{SQLParserFeature.KeepComments, SQLParserFeature.EnableSQLBinaryOpExprGroup});
        List<SQLStatement> statementList = parser.parseStatementList();
        System.out.println(SQLUtils.toSQLString(statementList, dbType, null, null));
    }

}
