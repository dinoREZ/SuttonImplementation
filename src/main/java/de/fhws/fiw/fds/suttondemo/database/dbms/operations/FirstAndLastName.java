package de.fhws.fiw.fds.suttondemo.database.dbms.operations;

import org.apache.commons.lang.StringUtils;

public class FirstAndLastName
{
	private final String firstName;

	private final String lastName;

	public FirstAndLastName( final String firstName, final String lastName )
	{
		this.firstName = StringUtils.defaultIfEmpty( firstName, "" );
		this.lastName = StringUtils.defaultIfEmpty( lastName, "" );
	}

	public String getFirstName( )
	{
		return firstName;
	}

	public String getLastName( )
	{
		return lastName;
	}
}
