package de.fhws.fiw.fds.suttondemo.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractLoadByIdOperation;
import de.fhws.fiw.fds.suttondemo.database.dbms.MySqlPersistency;
import de.fhws.fiw.fds.suttondemo.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemo.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadPersonById extends AbstractLoadByIdOperation<Person>
{
	public LoadPersonById( )
	{
		super( MySqlPersistency.getInstance( ) );
	}

	@Override protected String getTableName( )
	{
		return PersonTable.TABLE_NAME;
	}

	@Override protected Person createModel( final ResultSet resultSet ) throws SQLException
	{
		return CreatePersonFromResultSet.convert( resultSet );
	}
}
