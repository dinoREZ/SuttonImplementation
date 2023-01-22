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

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The AbstractDeleteOperation class provides the required functionality to perform DELETE operations to delete
 * single data from a certain data table in the context of SQL databases used by a Sutton application.
 *
 * @see AbstractDatabaseOperation
 * */
public abstract class AbstractDeleteOperation extends AbstractDatabaseOperation<Long, NoContentResult>
{
	/**
	 * A precompiled SQL statement {@link PreparedStatement} that could be executed efficiently
	 * multiple times in the context of deleting single data in a certain database table
	 * */
	protected PreparedStatement databaseStatement;

	/**
	 * A {@link String} representing the SQL statement to be used to delete single data from a certain database table
	 * */
	protected String databaseSQLStatement;

	/**
	 * Constructs a delete-single-data operation and assigns the given persistency instance as the database, in which
	 * the delete operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractDeleteOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected NoContentResult executeDatabaseOperations( ) throws SQLException
	{
		createDatabaseSQLStatement( );

		createDatabaseStatement( );

		configureDatabaseStatement( );

		executeDatabaseStatement( );

		closeDatabaseStatement( );

		return new NoContentResult( );
	}

	/**
	 * Defines the SQL statement to perform a delete-single operation and assigns it to
	 * {@link AbstractDeleteOperation#databaseSQLStatement}
	 * */
	protected void createDatabaseSQLStatement( )
	{
		this.databaseSQLStatement = "DELETE FROM " + getTableName( ) + " WHERE id = ?";
	}

	/**
	 * Creates a precompiled SQL statement to perform a delete-single operation and assigns it to
	 * {@link AbstractDeleteOperation#databaseStatement}
	 * @throws SQLException if a database access error occurred or if the prepared statement
	 * was called on a closed connection to the database
	 * */
	protected void createDatabaseStatement( ) throws SQLException
	{
		this.databaseStatement = this.databaseConnection.prepareStatement( this.databaseSQLStatement );
	}

	/**
	 * Replaces the parameter within the preparedStatement {@link AbstractDeleteOperation#databaseStatement} by
	 * the id of the data that should be deleted from the data table.
	 * @throws SQLException if parameterIndex does not correspond to a parameter marker in the SQL statement;
	 * if a database access error occurs or this method is called on a closed PreparedStatement
	 * */
	protected void configureDatabaseStatement( ) throws SQLException
	{
		this.databaseStatement.setLong( 1, this.params );
	}

	/**
	 * Executes the delete-single operation
	 * @throws SQLException if a database access error occurred, or if the delete operation
	 * was called on a closed PreparedStatement, or if the execution of the delete operation
	 * returned data
	 * */
	protected void executeDatabaseStatement( ) throws SQLException
	{
		this.databaseStatement.executeUpdate( );
	}

	/**
	 * Releases the prepared database statement {@link AbstractDeleteOperation#databaseStatement} that is used
	 * to perform the delete-single operation
	 * @throws SQLException if a database access error occurred
	 * */
	protected void closeDatabaseStatement( ) throws SQLException
	{
		if ( this.databaseSQLStatement != null )
		{
			this.databaseStatement.close( );
		}
	}

	/**
	 * Defines the table, in which the delete-single operation should be executed
	 * @return the table name {@link String}, to delete all its data.
	 * */
	protected abstract String getTableName( );

	@Override protected NoContentResult createDatabaseError( )
	{
		final NoContentResult returnValue = new NoContentResult( );
		returnValue.setError( );
		return returnValue;
	}
}
