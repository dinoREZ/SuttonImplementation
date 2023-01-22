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
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The AbstractWriteOperation class defines the fundamental requirements to perform write operations to create or
 * update the data in a certain data table in the context of SQL databases used by a Sutton application
 *
 * @param <P> the parameters to be used to write data in the database
 * @see AbstractDatabaseOperation
 * */
public abstract class AbstractWriteOperation<P extends AbstractModel>
	extends AbstractDatabaseOperation<P, NoContentResult>
{
	/**
	 * A precompiled SQL statement {@link PreparedStatement} that could be executed efficiently
	 * multiple times in the context of writing data in a certain database table
	 * */
	protected PreparedStatement databaseStatement;

	/**
	 * A {@link String} representing the SQL statement to be used to write data in a certain database table
	 * */
	protected String databaseSQLStatement;

	/**
	 * Constructs a write operation and assigns the given persistency instance as the database, in which
	 * the writing operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractWriteOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected NoContentResult executeDatabaseOperations( ) throws SQLException
	{
		createDatabaseSqlStatement( );

		createDatabaseStatement( );

		configureDatabaseStatement( );

		executeSQLStatement( );

		closeDatabaseStatement( );

		return new NoContentResult( );
	}

	/**
	 * Defines the SQL statement to perform a writing operation and assigns it to
	 * {@link AbstractWriteOperation#databaseSQLStatement}
	 * @throws SQLException if a database access error occurs, this method is called on a closed connection
	 * 	 * or the given parameter is not a Statement constant indicating whether auto-generated keys should be returned
	 * */
	protected abstract void createDatabaseStatement( ) throws SQLException;

//	TODO: this method doesn't produce an exception
	/**
	 * Creates a precompiled SQL statement to perform a writing operation and assigns it to
	 * {@link AbstractWriteOperation#databaseStatement}
	 * @throws SQLException if a database access error occurred or if the prepared statement
	 * was called on a closed connection to the database
	 * */
	protected abstract void createDatabaseSqlStatement( ) throws SQLException;

	/**
	 * Executes the writing operation
	 * @throws SQLException if a database access error occurred, or if the writing operation
	 * was called on a closed PreparedStatement, or if the execution of the write operation
	 * returned data
	 * */
	protected abstract void executeSQLStatement( ) throws SQLException;

	/**
	 * Configures the SQL statement {@link AbstractWriteOperation#databaseSQLStatement} to prepare it to write data in a
	 * certain data table
	 * @throws SQLException if parameterIndex, where the placeholder should be replaced by the value to use it to write
	 * the data, does not correspond to a parameter marker in the SQL statement;
	 * if a database access error occurs or this method is called on a closed PreparedStatement
	 * */
	protected void configureDatabaseStatement( ) throws SQLException
	{
		setValuesToPreparedStatement( );
	}

	/**
	 * Replaces the placeholders in the SQL statement {@link AbstractWriteOperation#databaseStatement} with the
	 * values of the attributes of an instance of {@link AbstractModel} and prepare this SQL statement for execution
	 * @throws SQLException if parameterIndex does not correspond to a parameter marker in the SQL statement;
	 * if a database access error occurs or this method is called on a closed {@link AbstractWriteOperation#databaseStatement}
	 * */
	protected abstract void setValuesToPreparedStatement( ) throws SQLException;

	/**
	 * Replaces a specific placeholder (defined by the given index parameter) in the {@link AbstractWriteOperation#databaseStatement}
	 * with the given value.
	 * @param value the value ,by which the placeholder in the SQL statement should be replaced
	 * @param index the index of the placeholder in the SQL statement to be replaced by the given value
	 * @throws    SQLException if parameterIndex does not correspond to a parameter marker in the SQL statement;
	 * 	 * if a database access error occurs or this method is called on a closed
	 * 	 {@link AbstractWriteOperation#databaseStatement}
	 * */
	protected void defineColumnValueByIndex( final int index, final Object value )
		throws SQLException
	{
		PreparedStatementHelper.set( this.databaseStatement, index, value );
	}

	@Override protected NoContentResult createDatabaseError( )
	{
		final NoContentResult returnValue = new NoContentResult( );
		returnValue.setError( );
		return returnValue;
	}

	/**
	 * Defines the table name to write the data in it.
	 * @return the table name {@link String} in the database
	 * */
	protected abstract String getTableName( );

	private void closeDatabaseStatement( ) throws SQLException
	{
		if ( this.databaseStatement != null )
		{
			this.databaseStatement.close( );
		}
	}
}
