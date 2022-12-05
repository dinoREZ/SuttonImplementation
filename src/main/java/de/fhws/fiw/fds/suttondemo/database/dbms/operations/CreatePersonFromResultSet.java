package de.fhws.fiw.fds.suttondemo.database.dbms.operations;

import de.fhws.fiw.fds.suttondemo.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemo.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatePersonFromResultSet
{
	public static Person convert( final ResultSet resultSet ) throws SQLException
	{
		final Person returnValue = new Person( );

		returnValue.setId( resultSet.getLong( PersonTable.COL_ID ) );
		returnValue.setFirstName( resultSet.getString( PersonTable.COL_FIRSTNAME ) );
		returnValue.setLastName( resultSet.getString( PersonTable.COL_LASTNAME ) );
		returnValue.setBirthDate( resultSet.getDate( PersonTable.COL_BIRTHDATE ).toLocalDate( ) );
		returnValue.setEmailAddress( resultSet.getString( PersonTable.COL_EMAIL ) );

		return returnValue;
	}
}
