/*
 * Copyright (c) peter.braun@fhws.de
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.sutton.server.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.IPersistency;
import de.fhws.fiw.fds.sutton.server.database.dbms.tables.AbstractTable;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.sql.SQLException;

/**
 * The AbstractLoadByIdOperation class optimizes the functionality of AbstractLoadSingleOperation to load
 * single data <strong>by its id</strong> from a certain data table in the context of SQL databases used by a Sutton
 * application.
 *
 * @param <R> the single model loaded from the database after successfully executing the load single data by id operation
 * @see AbstractLoadSingleOperation
 * */
public abstract class AbstractLoadByIdOperation<R extends AbstractModel> extends AbstractLoadSingleOperation<Long, R>
{
	/**
	 * Constructs a load-single-by-id operation and assigns the given persistency instance as the database, in which
	 * the loading operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractLoadByIdOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected String sqlStatementWithoutSelectStartingAfterFrom( )
	{
		return getTableName( ) + " WHERE " + AbstractTable.COL_ID + " = ?";
	}

	@Override protected void setQueryParametersToPreparedStatement( ) throws SQLException
	{
		this.databaseStatement.setLong( 1, this.params );
	}

	/**
	 * Defines the table name, that should be used in the SQL statement to perform.
	 * @return the table name {@link String}  in the database
	 * */
	protected abstract String getTableName( );

}
