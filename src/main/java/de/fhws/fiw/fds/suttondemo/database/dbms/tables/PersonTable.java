package de.fhws.fiw.fds.suttondemo.database.dbms.tables;

import de.fhws.fiw.fds.sutton.server.database.dbms.IPersistency;
import de.fhws.fiw.fds.sutton.server.database.dbms.tables.AbstractTable;

import java.util.Arrays;
import java.util.List;

public class PersonTable extends AbstractTable
{
	public static final String TABLE_NAME = "Person";
	public static final String COL_FIRSTNAME = "firstName";
	public static final int COL_FIRSTNAME_IDX = 1;
	public static final String COL_LASTNAME = "lastName";
	public static final int COL_LASTNAME_IDX = 2;
	public static final String COL_BIRTHDATE = "birthday";
	public static final int COL_BIRTHDATE_IDX = 3;
	public static final String COL_EMAIL = "emailAddress";
	public static final int COL_EMAIL_IDX = 4;

	/*
	 * The order of the columns MUST be according to the index above.
	 * You can change the index but then you have to change the order as well.
	 */
	public static final List<String> ALL_COLUMNS = Arrays.asList(
		PersonTable.COL_FIRSTNAME,
		PersonTable.COL_LASTNAME,
		PersonTable.COL_BIRTHDATE,
		PersonTable.COL_EMAIL
	);

	public PersonTable( final IPersistency persistency )
	{
		super( persistency );
	}

	@Override protected String getTableName( )
	{
		return TABLE_NAME;
	}

	@Override protected void appendColumns( final StringBuffer sb )
	{
		sb.append( COL_FIRSTNAME + " varchar(40)," );
		sb.append( COL_LASTNAME + " varchar(100)," );
		sb.append( COL_BIRTHDATE + " date," );
		sb.append( COL_EMAIL + " varchar(100)," );
	}

}
