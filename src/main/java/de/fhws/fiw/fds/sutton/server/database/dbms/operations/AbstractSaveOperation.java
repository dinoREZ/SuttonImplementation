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
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * The AbstractSaveOperation class optimizes the functionality of AbstractWriteOperation to insert a
 * single data in a certain data table in the context of SQL databases used by a Sutton application.
 *
 * @param <P> the parameters to be used to create a single data in the database
 * @see AbstractWriteOperation
 * */
public abstract class AbstractSaveOperation<P extends AbstractModel> extends
	AbstractWriteOperation<P>
{
	/**
	 * Constructs a save operation and assigns the given persistency instance as the database, in which
	 * the insert operation should be executed
	 * @param persistency the database instance to be used to persist data
	 * */
	public AbstractSaveOperation( final IPersistency persistency )
	{
		super( persistency );
	}

	/**
	 * Creates a precompiled SQL statement to perform an insert operation and assigns it to
	 * {@link AbstractSaveOperation#databaseStatement}
	 * @throws SQLException if a database access error occurs, this method is called on a closed connection
	 * or the given parameter is not a Statement constant indicating whether auto-generated keys should be returned
	 * */
	protected final void createDatabaseStatement( ) throws SQLException
	{
		this.databaseStatement =
			this.databaseConnection.prepareStatement( this.databaseSQLStatement, RETURN_GENERATED_KEYS );
	}

	/**
	 * Defines the SQL statement to perform an insert operation and assigns it to
	 * {@link AbstractSaveOperation#databaseSQLStatement}
	 * @throws SQLException if a database access error occurs, this method is called on a closed connection
	 * or the given parameter is not a Statement constant indicating whether auto-generated keys should be returned
	 * */
	protected final void createDatabaseSqlStatement( ) throws SQLException
	{
		final StringBuffer sqlStmt = new StringBuffer( );

		sqlStmt.append( "INSERT INTO " );
		sqlStmt.append( getTableName( ) );
		sqlStmt.append( "(" );
		sqlStmt.append( columnsNamesSeparatedByComma( ) );
		sqlStmt.append( ")" );
		sqlStmt.append( "VALUES(" );
		sqlStmt.append( columnValuesReplacedByQuestionMarksAndSeparatedByComma( ) );
		sqlStmt.append( ")" );

		this.databaseSQLStatement = sqlStmt.toString( );
	}

	/**
	 * Specifies the columns' names of the data entity that should be inserted in the database
	 * @return a list of columns' names
	 * */
	protected abstract List<String> columnNames( );

	/**
	 * Executes the insert operation
	 * @throws SQLException if a database access error occurred, or if the insert operation
	 * was called on a closed {@link AbstractSaveOperation#databaseStatement}, or if the execution of the insert
	 * operation returned data
	 * */
	protected final void executeSQLStatement( ) throws SQLException
	{
		this.databaseStatement.executeUpdate( );
		this.params.setId( getNewId( ) );
	}

	private long getNewId( ) throws SQLException
	{
		final ResultSet resultSet = this.databaseStatement.getGeneratedKeys( );
		resultSet.next( );
		return resultSet.getLong( 1 );
	}

	private String columnsNamesSeparatedByComma( )
	{
		return columnNames( ).stream( ).collect( Collectors.joining( "," ) );
	}

	private String columnValuesReplacedByQuestionMarksAndSeparatedByComma( )
	{
		return columnNames( ).stream( )
							 .map( c -> "?" )
							 .collect( Collectors.joining( "," ) );
	}
}
