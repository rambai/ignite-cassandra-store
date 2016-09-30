/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.szczurmys.ignite.tests;

import com.github.szczurmys.ignite.tests.utils.CassandraHelper;
import org.apache.log4j.Logger;

/**
 * Simple helper class to run Cassandra on localhost
 */
public class CassandraLocalServer {
    /** */
    private static final Logger LOGGER = Logger.getLogger(CassandraLocalServer.class.getName());

    /** */
    public static void main(String[] args) {
        try {
            CassandraHelper.startEmbeddedCassandra(LOGGER);
        }
        catch (Throwable e) {
            throw new RuntimeException("Failed to start embedded Cassandra instance", e);
        }

        LOGGER.info("Testing admin connection to Cassandra");
        CassandraHelper.testAdminConnection();

        LOGGER.info("Testing regular connection to Cassandra");
        CassandraHelper.testRegularConnection();

        LOGGER.info("Dropping all artifacts from previous tests execution session");
        CassandraHelper.dropTestKeyspaces();

        while (true) {
            try {
                System.out.println("Cassandra server running");
                Thread.sleep(10000);
            }
            catch (Throwable e) {
                throw new RuntimeException("Cassandra server terminated", e);
            }
        }
    }
}