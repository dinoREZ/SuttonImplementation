/*
 * Copyright 2019 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fhws.fiw.fds.sutton.server.database.dbms.tables;

import de.fhws.fiw.fds.sutton.server.database.dbms.IPersistency;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The AbstractTable class provides the required functionality to create data tables in a MySQL database to persist
 * data for the Sutton framework
 * */
public abstract class AbstractTable
{
	/**
	 * A unique identifier {@link String} for each entry in the data table
	 * */
	public static final String COL_ID = "id";

	private final IPersistency persistency;

	/**
	 * Constructs an instance of AbstractTable using an instance of {@link IPersistency} , which represents the
	 * MySQL database used by the Sutton framework, in order to create the table in it.
	 * @param persistency an instance of {@link IPersistency} describing the MySQL database
	 * */
	protected AbstractTable( final IPersistency persistency )
	{
		this.persistency = persistency;
	}

	/**
	 * Creates a data table in the MySQL database used by the Sutton framework using {@link AbstractTable#COL_ID} as a
	 * primary key for this table
	 * */
	public void createTable( )
	{
		final StringBuffer sb = new StringBuffer( );
		sb.append( "CREATE TABLE IF NOT EXISTS " )
		  .append( getTableName( ) )
		  .append( " ( " + COL_ID + " bigint unsigned NOT NULL AUTO_INCREMENT," );

		appendColumns( sb );

		sb.append( getPrimaryKey( ) );
		sb.append( ")" );

		try
		{
			final Connection con = this.persistency.getConnection( );
			final Statement stmt = con.createStatement( );
			stmt.executeUpdate( sb.toString( ) );
		}
		catch ( final SQLException e )
		{
			e.printStackTrace( );
		}
	}

	/**
	 * Returns a string used to set the primary key for the table
	 * @return the primary key for the table {@link String}
	 * */
	protected String getPrimaryKey( )
	{
		return "PRIMARY KEY (`" + COL_ID + "`)";
	}

	/**
	 * @return the table name {@link String}
	 * */
	protected abstract String getTableName( );

	/**
	 * Appends columns name to the MySQL statement used to create the data table. The method can be used to define
	 * the name and data type of the columns in the table.
	 * @param sb the MySQL statement for creating the table {@link StringBuffer} to append the columns' names to it
	 * */
	protected abstract void appendColumns( StringBuffer sb );
}
