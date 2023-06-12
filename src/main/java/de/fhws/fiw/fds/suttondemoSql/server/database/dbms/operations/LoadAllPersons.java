package de.fhws.fiw.fds.suttondemoSql.server.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractLoadCollectionOperation;
import de.fhws.fiw.fds.suttondemoSql.server.database.dbms.MySqlPersistency;
import de.fhws.fiw.fds.suttondemoSql.server.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemoSql.server.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadAllPersons extends AbstractLoadCollectionOperation<Void, Person> {
	public LoadAllPersons() {
		super(MySqlPersistency.getInstance());
	}

	@Override
	protected String createSQLStatementWithoutSelectStartingAfterFrom() {
		final StringBuffer returnValue = new StringBuffer();

		returnValue.append(PersonTable.TABLE_NAME);

		return returnValue.toString();
	}

	@Override
	protected Person createModel(final ResultSet resultSet) throws SQLException {
		return CreatePersonFromResultSet.convert(resultSet);
	}

	@Override
	protected void setQueryParametersToPreparedStatement() throws SQLException {

	}
}
