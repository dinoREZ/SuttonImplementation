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

package de.fhws.fiw.fds.sutton.server.database.dbms;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The AbstractMySqlPersistence class provides the required functionality to establish a connection to a MySQL data
 * source. The methods presented by this class should be used to define the name, username, and password of the MySQL
 * database to be used to persist data for the Sutton framework. Additionally, the class is used to create all
 * necessary tables
 *
 * */
public abstract class AbstractMySqlPersistence implements IPersistency {
	private ComboPooledDataSource cpds;

	/**
	 * Constructs an instance of AbstractMySqlPersistence. It also establishes a connection to a MySQL data source
	 * and creates all the necessary tables.
	 * */
	protected AbstractMySqlPersistence() {
		createConnectionPool();
		createAllTables();
	}

	/**
	 * Establishes the connection to a MySQL database
	 * */
	public void createConnectionPool() {
		try {
			this.cpds = new ComboPooledDataSource();
			this.cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
			this.cpds.setJdbcUrl("jdbc:mysql://" + getHostNameAndPort() + "/" + getDatabaseName());
			this.cpds.setUser(getDatabaseUser());
			this.cpds.setPassword(getDatabasePassword());
			this.cpds.setMinPoolSize(5);
			this.cpds.setAcquireIncrement(5);
			this.cpds.setMaxPoolSize(20);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		this.cpds.close();
	}

	/**
	 * Returns a string describing the host name and the port of the MySQL database
	 * @return the host name and the port, on which the MySQL database is running
	 * */
	protected String getHostNameAndPort() {
		return "localhost:3306";
	}

	/**
	 * @return the database name {@link String}
	 * */
	protected abstract String getDatabaseName();

	/**
	 * @return the database user's name {@link String}
	 * */
	protected abstract String getDatabaseUser();

	/**
	 * @return the password for the database {@link String}
	 * */
	protected abstract String getDatabasePassword();

	/**
	 * Creates all necessary database tables
	 * */
	protected abstract void createAllTables();

	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}
}
