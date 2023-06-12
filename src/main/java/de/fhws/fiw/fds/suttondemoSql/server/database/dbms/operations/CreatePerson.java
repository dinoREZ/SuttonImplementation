package de.fhws.fiw.fds.suttondemoSql.server.database.dbms.operations;

import de.fhws.fiw.fds.sutton.server.database.dbms.operations.AbstractSaveOperation;
import de.fhws.fiw.fds.suttondemoSql.server.database.dbms.MySqlPersistency;
import de.fhws.fiw.fds.suttondemoSql.server.database.dbms.tables.PersonTable;
import de.fhws.fiw.fds.suttondemoSql.server.database.dbms.tables.SetParametersInSqlStatement;
import de.fhws.fiw.fds.suttondemoSql.server.models.Person;

import java.sql.SQLException;
import java.util.List;

public class CreatePerson extends AbstractSaveOperation<Person> {
	public CreatePerson() {
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
