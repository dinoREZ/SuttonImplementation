package de.fhws.fiw.fds.suttondemoSql.server.database.dbms.operations;

import de.fhws.fiw.fds.suttondemoSql.server.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemoSql.server.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CreatePersonFromResultSet {
	public static Person convert(final ResultSet resultSet) throws SQLException {
		final Person returnValue = new Person();

		returnValue.setId(resultSet.getLong(PersonTable.COL_ID));
		returnValue.setFirstName(resultSet.getString(PersonTable.COL_FIRSTNAME));
		returnValue.setLastName(resultSet.getString(PersonTable.COL_LASTNAME));
		returnValue.setBirthDate(Optional.ofNullable(resultSet.getDate(PersonTable.COL_BIRTHDATE))
				.map(date -> date.toLocalDate()).orElse(null));
		returnValue.setEmailAddress(resultSet.getString(PersonTable.COL_EMAIL));

		return returnValue;
	}
}
