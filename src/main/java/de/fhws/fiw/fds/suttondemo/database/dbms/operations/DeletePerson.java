package de.fhws.fiw.fds.suttondemo.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractDeleteOperation;
import de.fhws.fiw.fds.suttondemo.database.dbms.MySqlPersistency;
import de.fhws.fiw.fds.suttondemo.database.dbms.tables.PersonTable;

public class DeletePerson extends AbstractDeleteOperation
{
	public DeletePerson( )
	{
		super( MySqlPersistency.getInstance( ) );
	}

	@Override protected String getTableName( )
	{
		return PersonTable.TABLE_NAME;
	}
}
