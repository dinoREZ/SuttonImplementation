package de.fhws.fiw.fds.suttondemo.server.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractLoadCollectionOperation;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.MySqlPersistency;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemo.server.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadPersonsByFirstNameAndLastName extends AbstractLoadCollectionOperation<FirstAndLastName, Person> {
	public LoadPersonsByFirstNameAndLastName() {
		super(MySqlPersistency.getInstance());
	}

	@Override
	protected String createSQLStatementWithoutSelectStartingAfterFrom() {
		final StringBuffer returnValue = new StringBuffer();

		/*
		 * The following SQL statement searches for Persons by first name and last name.
		 * It takes into account that either first name or last name or both can be
		 * empty
		 * strings. First, it is checked whether the query parameter is empty OR (if
		 * not)
		 * the query parameter is equal to the value in the column.
		 */
		returnValue.append(PersonTable.TABLE_NAME);
		returnValue.append(" WHERE ");
		returnValue.append("( ? = '' OR " + PersonTable.COL_FIRSTNAME + " = ? )");
		returnValue.append("AND ");
		returnValue.append("( ? = '' OR " + PersonTable.COL_LASTNAME + " = ? )");

		return returnValue.toString();
	}

	@Override
	protected void setQueryParametersToPreparedStatement() throws SQLException {
		this.databaseStatement.setString(1, this.params.getFirstName());
		this.databaseStatement.setString(2, this.params.getFirstName());
		this.databaseStatement.setString(3, this.params.getLastName());
		this.databaseStatement.setString(4, this.params.getLastName());
	}

	@Override
	protected Person createModel(final ResultSet resultSet) throws SQLException {
		return CreatePersonFromResultSet.convert(resultSet);
	}
}
