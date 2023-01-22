// Copyright 2022 Peter Braun
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.sutton.server.database.dbms.operations;

/**
 * The AbstractDatabaseSearchParameters class defines the basic requirements to define the parameters
 * to be used in a SQL read statement to fetch data from the database
 * */
public abstract class AbstractDatabaseSearchParameters {

    private int offset;

    private int size;

    private String orderBy;

    /**
     * Constructs an AbstractDatabaseSearchParameters setting the offset to start reading data from a data table, and
     * the size of the data to be read, to the given values
     * @param size {@link Integer} the amount of data to be read from a data table
     * @param offset {@link Integer} the offset to start reading data from a data table
     * */
    protected AbstractDatabaseSearchParameters(int offset, int size) {
        this.offset = offset;
        this.size = size;
        this.orderBy = "";
    }

    /**
     * @return the offset {@link Integer} to start reading data from a data table
     * */
    public int getOffset() {
        return this.offset;
    }

    /**
     * @return the amount of data {@link Integer} to be read from a data table
     * */
    public int getSize() {
        return this.size;
    }

    /**
     * @return {@link String} describing the criteria to sort the data read from a data table
     * */
    public String getOrderBy() {
        return this.orderBy;
    }
}