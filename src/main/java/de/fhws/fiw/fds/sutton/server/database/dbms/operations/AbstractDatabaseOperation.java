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
import de.fhws.fiw.fds.sutton.server.database.results.AbstractResult;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The AbstractDatabaseOperation class provides the fundamental requirements to create all different CRUD
 * operations in the context of SQL databases. The class defines the structure to establish a connection to an
 * SQL database, to process and execute the different CRUD operations, and to handle possible errors that could occur
 * during the execution.
 *
 * @param <P> the parameters for the SQL statements
 * @param <R> the result after executing a CRUD operation
 * */
public abstract class AbstractDatabaseOperation<P, R extends AbstractResult>
{
	/**
	 * The database instance {@link IPersistency} to be used by a sutton application
	 * */
	protected final IPersistency mysql;

	/**
	 * A connection (session) {@link Connection} to the database, SQL statements are executed and the results of the
	 * execution of these statements are returned within the context of this connection
	 * */
	protected Connection databaseConnection;

	/**
	 * The parameters to be used within the SQL statements
	 * */
	protected P params;

	/**
	 * Constructs a database operation setting its database instance to the given value
	 * @param persistency the database instance to be used to persist data
	 * */
	protected AbstractDatabaseOperation( final IPersistency persistency )
	{
		this.mysql = persistency;
	}

	/**
	 * Executes a CRUD operation and returns the result of performing this operation
	 * @param param the parameter to use within a CRUD operation that should be executed
	 * @return {@link R} an instance of AbstractResult to send it in the response to the client
	 *
	 * @see AbstractResult
	 * */
	public final R execute( final P param )
	{
		this.params = param;

		try
		{
			return _process( );
		}
		catch ( final SQLException e )
		{
			return handleExceptions( e );
		}
		finally
		{
			closeConnection( );
		}
	}

	private R _process( ) throws SQLException
	{
		final long startTime = System.currentTimeMillis( );

		preProcessModel( );

		this.databaseConnection = this.mysql.getConnection( );

		this.databaseConnection.setAutoCommit( false );

		final R returnValue = executeDatabaseOperations( );

		final long stopTime = System.currentTimeMillis( );

		returnValue.setTimes( startTime, stopTime );

		this.databaseConnection.commit( );

		return returnValue;
	}

	/**
	 * This method could be used to configure the SQL statement before starting a connection to the
	 * database to execute this statement
	 * */
	protected void preProcessModel( )
	{

	}

	/**
	 * Defines how a database operation should be executed and returns the result of performing this operation
	 * @return {@link R} an instance of AbstractResult to send it in the response to the client
	 * @throws SQLException if an error occurred while executing the database operation
	 * */
	protected abstract R executeDatabaseOperations( ) throws SQLException;

	private R handleExceptions( final SQLException e )
	{
		e.printStackTrace( );
		if ( this.databaseConnection != null )
		{
			try
			{
				this.databaseConnection.rollback( );
			}
			catch ( final Exception ee )
			{
				ee.printStackTrace( );
			}
		}

		return createDatabaseError( );
	}

	private void closeConnection( )
	{
		if ( this.databaseConnection != null )
		{
			try
			{
				this.databaseConnection.close( );
			}
			catch ( final Exception ee )
			{
				ee.printStackTrace( );
			}
		}
	}

	/**
	 * Creates a result, describing that the execution of a database operation has failed.
	 * @return {@link R} an instance of AbstractResult to send it in the response to the client
	 * to inform them, that the database operation was not executed successfully
	 * */
	protected abstract R createDatabaseError( );

}
