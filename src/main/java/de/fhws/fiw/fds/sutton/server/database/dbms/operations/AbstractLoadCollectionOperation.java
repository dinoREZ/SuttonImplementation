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
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

/**
 * The AbstractLoadCollectionOperation class optimizes the functionality of AbstractLoadOperation to load
 * a data collection from a certain data table in the context of SQL databases used by a Sutton application.
 *
 * @param <P> the parameters to be used to load single data from the database
 * @param <M> the collection of models loaded from the database after successfully executing the load collection operation
 * @see AbstractLoadOperation
 * */
public abstract class AbstractLoadCollectionOperation<P, M extends AbstractModel>
	extends
	AbstractLoadOperation<P, CollectionModelResult<M>>
{
	/**
	 * Constructs a load-collection operation and assigns the given persistency instance as the database, in which
	 * the loading operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractLoadCollectionOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected CollectionModelResult<M> executeDatabaseOperations( ) throws SQLException
	{
		final CollectionModelResult<M> returnValue = super.executeDatabaseOperations( );
		returnValue.setTotalNumberOfResult( getTotalNumberOfRows( ) );
		return returnValue;
	}

	@Override
	protected void createDatabaseSQLStatement( )
	{
		final StringBuffer returnValue = new StringBuffer( );

		returnValue.append( "SELECT SQL_CALC_FOUND_ROWS " + columns( ) + " FROM " );
		returnValue.append( createSQLStatementWithoutSelectStartingAfterFrom( ) );

		this.databaseSQLStatement = returnValue.toString( );
	}

	/**
	 * Defines the columns' names in the SQL statement, by which the data should be loaded from the database
	 * @return the columns' names {@link String} to be loaded from the database
	 * */
	protected String columns( )
	{
		return "*";
	}

	/**
	 * Creates the part of the SQL statement that comes after the <strong>"SELECT * FROM "</strong> part. The method
	 * specifies the table name and the criteria, by which the data should be loaded from the database
	 * @return a {@link String} specifying the table name optionally the criteria to use it to load the data
	 * from the database
	 * */
	protected abstract String createSQLStatementWithoutSelectStartingAfterFrom( );

	private int getTotalNumberOfRows( ) throws SQLException
	{
		final Statement stmt = this.databaseConnection.createStatement( );
		final String sqlStmt = "SELECT FOUND_ROWS()";
		final ResultSet resultSet = stmt.executeQuery( sqlStmt );

		resultSet.next( );

		final int totalNumberOfResults = resultSet.getInt( 1 );

		resultSet.close( );
		stmt.close( );

		return totalNumberOfResults;
	}

	@Override
	protected void processResultSet( ) throws SQLException
	{
		final Collection<M> returnValue = new LinkedList<>( );

		while ( this.resultSet.next( ) )
		{
			returnValue.add( createModel( this.resultSet ) );
		}

		this.queryResult = new CollectionModelResult<>( returnValue );
	}

	/**
	 * Creates an instance of {@link AbstractModel} from the values within the data table
	 * {@link AbstractLoadCollectionOperation#resultSet} that was loaded from the database after performing the load
	 * operation
	 * @return a collection of {@link M} loaded from the database
	 * @throws SQLException if an error occurs while trying to read the values of the columns from the
	 * {@link AbstractLoadCollectionOperation#resultSet} or if this ResultSet was closed
	 * */
	protected abstract M createModel( ResultSet resultSet ) throws SQLException;

	@Override protected CollectionModelResult<M> createDatabaseError( )
	{
		final CollectionModelResult returnValue = new CollectionModelResult( );
		returnValue.setError( );
		return returnValue;
	}
}
