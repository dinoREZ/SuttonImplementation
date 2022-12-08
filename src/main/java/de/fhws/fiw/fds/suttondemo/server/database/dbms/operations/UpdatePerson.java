package de.fhws.fiw.fds.suttondemo.server.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractUpdateOperation;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.MySqlPersistency;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.tables.SetParametersInSqlStatement;
import de.fhws.fiw.fds.suttondemo.server.models.Person;

import java.sql.SQLException;
import java.util.List;

public class UpdatePerson extends AbstractUpdateOperation<Person> {
	public UpdatePerson() {
		super(MySqlPersistency.getInstance());
	}

	@Override
	protected List<String> columnNames() {
		return PersonTable.ALL_COLUMNS;
	}

	@Override
	protected void setValuesToPreparedStatement() throws SQLException {
		new SetParametersInSqlStatement().set(this.params, this.databaseStatement);
	}

	@Override
	protected String getTableName() {
		return PersonTable.TABLE_NAME;
	}
}
