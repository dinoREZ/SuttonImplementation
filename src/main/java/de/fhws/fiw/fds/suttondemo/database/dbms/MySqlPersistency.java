package de.fhws.fiw.fds.suttondemo.database.dbms;

import de.fhws.fiw.fds.sutton.server.database.dbms.AbstractMySqlPersistence;
import de.fhws.fiw.fds.sutton.server.database.dbms.IPersistency;
import de.fhws.fiw.fds.suttondemo.database.dbms.tables.PersonTable;

public class MySqlPersistency extends AbstractMySqlPersistence
{
	private static IPersistency INSTANCE;

	public static IPersistency getInstance( )
	{
		if ( INSTANCE == null )
		{
			INSTANCE = new MySqlPersistency( );
		}

		return INSTANCE;
	}

	private MySqlPersistency( )
	{
		super( );
	}

	@Override protected String getDatabaseName( )
	{
		return "PersonDemo";
	}

	@Override protected String getDatabaseUser( )
	{
		return "demouser";
	}

	@Override protected String getDatabasePassword( )
	{
		return "password";
	}

	@Override protected void createAllTables( )
	{
		new PersonTable( this ).createTable( );
	}
}
