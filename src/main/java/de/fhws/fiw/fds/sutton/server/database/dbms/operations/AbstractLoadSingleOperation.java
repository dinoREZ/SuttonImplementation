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
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The AbstractLoadSingleOperation class optimizes the functionality of AbstractLoadOperation to load
 * single data from a certain data table in the context of SQL databases used by a Sutton application.
 *
 * @param <P> the parameters to be used to load single data from the database
 * @param <R> the single model loaded from the database after successfully executing the load single operation
 * @see AbstractLoadOperation
 * */
public abstract class AbstractLoadSingleOperation<P, R extends AbstractModel>
	extends AbstractLoadOperation<P, SingleModelResult<R>>
{

	/**
	 * Constructs a load-single operation and assigns the given persistency instance as the database, in which
	 * the loading operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractLoadSingleOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected void processResultSet( ) throws SQLException
	{
		if ( this.resultSet.next( ) )
		{
			this.queryResult = new SingleModelResult<>( createModel( this.resultSet ) );
		}
		else
		{
			this.queryResult = new SingleModelResult<>( );
		}
	}

	@Override protected void createDatabaseSQLStatement( ) throws SQLException
	{
		final StringBuffer returnValue = new StringBuffer( );

		returnValue.append( "SELECT * FROM " );
		returnValue.append( sqlStatementWithoutSelectStartingAfterFrom( ) );

		this.databaseSQLStatement = returnValue.toString( );
	}

	/**
	 * Creates the part of the SQL statement that comes after the <strong>"SELECT * FROM "</strong> part. The method
	 * specifies the table name and the criteria, by which the data should be loaded from the database
	 * @return a {@link String} specifying the table name optionally the criteria to use it to load the data
	 * from the database
	 * */
	protected abstract String sqlStatementWithoutSelectStartingAfterFrom( );

	/**
	 * Creates an instance of {@link AbstractModel} from the single value within the data table
	 * {@link AbstractLoadSingleOperation#resultSet} that was loaded from the database after performing the load
	 * operation
	 * @return a single model {@link R}
	 * @throws SQLException if an error occurs while trying to read the values of the columns from the
	 * {@link AbstractLoadSingleOperation#resultSet} or if this ResultSet was closed
	 * */
	protected abstract R createModel( ResultSet resultSet ) throws SQLException;

	@Override protected SingleModelResult<R> createDatabaseError( )
	{
		final SingleModelResult returnValue = new SingleModelResult( );
		returnValue.setError( );
		return returnValue;
	}
}
