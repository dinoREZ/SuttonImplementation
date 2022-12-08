package de.fhws.fiw.fds.suttondemo.server.database.dbms;

import org.apache.commons.lang.StringUtils;

import de.fhws.fiw.fds.sutton.server.database.dbms.AbstractMySqlPersistence;
import de.fhws.fiw.fds.sutton.server.database.dbms.IPersistency;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.tables.PersonTable;

public class MySqlPersistency extends AbstractMySqlPersistence {
	private static IPersistency INSTANCE;

	public static IPersistency getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MySqlPersistency();
		}

		return INSTANCE;
	}

	private MySqlPersistency() {
		super();
	}

	@Override
	protected String getHostNameAndPort() {
		final String env = System.getenv("SUTTON_DB_HOST_PORT");
		System.out.println("ENV: " + env);
		return StringUtils.defaultIfBlank(env, super.getHostNameAndPort());
	}

	@Override
	protected String getDatabaseName() {
		return "PersonDemo";
	}

	@Override
	protected String getDatabaseUser() {
		return "demouser";
	}

	@Override
	protected String getDatabasePassword() {
		return "password";
	}

	@Override
	protected void createAllTables() {
		new PersonTable(this).createTable();
	}
}
