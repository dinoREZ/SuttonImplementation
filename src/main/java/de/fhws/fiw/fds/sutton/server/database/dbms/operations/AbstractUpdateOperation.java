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
import java.util.List;
import java.util.stream.Collectors;

/**
 * The AbstractUpdateOperation class optimizes the functionality of AbstractWriteOperation to update a
 * single data in a certain data table in the context of SQL databases used by a Sutton application.
 *
 * @param <P> the parameters to be used to update a single data in the database
 * @see AbstractWriteOperation
 * */
public abstract class AbstractUpdateOperation<P extends AbstractModel> extends AbstractWriteOperation<P>
{
	/**
	 * Constructs an update operation and assigns the given persistency instance as the database, in which
	 * the update operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractUpdateOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	/**
	 * Creates a precompiled SQL statement to perform an update operation and assigns it to
	 * {@link AbstractUpdateOperation#databaseStatement}
	 * @throws SQLException if a database access error occurs, this method is called on a closed connection
	 * */
	protected void createDatabaseStatement( ) throws SQLException
	{
		this.databaseStatement = this.databaseConnection.prepareStatement( this.databaseSQLStatement );
	}

	/**
	 * Defines the SQL statement to perform an update operation and assigns it to
	 * {@link AbstractUpdateOperation#databaseSQLStatement}
	 * */
	protected void createDatabaseSqlStatement( )
	{
		final StringBuffer sqlStmt = new StringBuffer( );

		sqlStmt.append( "UPDATE " );
		sqlStmt.append( getTableName( ) );
		sqlStmt.append( " SET " );
		sqlStmt.append( columnNamesEqualsQuestionMark( ) );
		sqlStmt.append( " WHERE " );
		sqlStmt.append( AbstractTable.COL_ID );
		sqlStmt.append( " = " );
		sqlStmt.append( this.params.getId( ) );

		this.databaseSQLStatement = sqlStmt.toString( );
	}

	/**
	 * Specifies the columns' names of the data entity that should be updated in the database
	 * @return a list of columns' names
	 * */
	protected abstract List<String> columnNames( );

	/**
	 * Executes the update operation
	 * @throws SQLException if a database access error occurred, or if the update operation
	 * was called on a closed {@link AbstractUpdateOperation#databaseStatement}, or if the execution of the update
	 * operation returned data
	 * */
	protected void executeSQLStatement( ) throws SQLException
	{
		this.databaseStatement.executeUpdate( );
	}

	private String columnNamesEqualsQuestionMark( )
	{
		return columnNames( ).stream( )
							 .map( c -> createColumnNameEqualsQuestionMark( c ) )
							 .collect( Collectors.joining( "," ) );
	}

	private String createColumnNameEqualsQuestionMark( final String columnName )
	{
		return columnName + " = ?";
	}

}
