package de.fhws.fiw.fds.suttondemo.server.database.dbms.operations;

import org.apache.commons.lang.StringUtils;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractDatabaseSearchParameters;

public class FirstAndLastName extends AbstractDatabaseSearchParameters {
	private final String firstName;

	private final String lastName;

	public FirstAndLastName(final String firstName, final String lastName, int offset, int size) {
		super(offset, size);
		this.firstName = StringUtils.defaultIfEmpty(firstName, "");
		this.lastName = StringUtils.defaultIfEmpty(lastName, "");
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
