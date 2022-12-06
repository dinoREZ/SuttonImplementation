package de.fhws.fiw.fds.suttondemo.server.database.dbms.tables;

import de.fhws.fiw.fds.sutton.server.database.dbms.tables.AbstractSetParametersInSqlStatement;
import de.fhws.fiw.fds.suttondemo.server.models.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SetParametersInSqlStatement extends AbstractSetParametersInSqlStatement<Person> {
	@Override
	protected Map<Integer, Function<Person, Object>> getMapOfIndexToGetterMethod() {
		final Map<Integer, Function<Person, Object>> returnValue = new HashMap<>();

		returnValue.put(PersonTable.COL_FIRSTNAME_IDX, Person::getFirstName);
		returnValue.put(PersonTable.COL_LASTNAME_IDX, Person::getLastName);
		returnValue.put(PersonTable.COL_BIRTHDATE_IDX, Person::getBirthDate);
		returnValue.put(PersonTable.COL_EMAIL_IDX, Person::getEmailAddress);

		return returnValue;
	}
}
