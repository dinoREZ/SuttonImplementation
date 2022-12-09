package de.fhws.fiw.fds.suttondemo.server.database.dbms;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.server.database.PersonDao;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.CreatePerson;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.DeleteAll;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.DeletePerson;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.FirstAndLastName;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.LoadAllPersons;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.LoadPersonById;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.LoadPersonsByFirstNameAndLastName;
import de.fhws.fiw.fds.suttondemo.server.database.dbms.operations.UpdatePerson;
import de.fhws.fiw.fds.suttondemo.server.models.Person;

public class PersonDaoImpl implements PersonDao {
	@Override
	public NoContentResult create(final Person model) {
		return new CreatePerson().execute(model);
	}

	@Override
	public SingleModelResult<Person> readById(final long id) {
		return new LoadPersonById().execute(id);
	}

	@Override
	public CollectionModelResult<Person> readAll(SearchParameter... searchParameters) {
		return new LoadAllPersons().execute((Void) null);
	}

	@Override
	public CollectionModelResult<Person> readByFirstNameAndLastName(final String firstName,
			final String lastName, SearchParameter searchParameter) {
		return new LoadPersonsByFirstNameAndLastName().execute(new FirstAndLastName(firstName,
				lastName, searchParameter.getOffset(), searchParameter.getSize()));
	}

	@Override
	public NoContentResult update(final Person model) {
		return new UpdatePerson().execute(model);
	}

	@Override
	public NoContentResult delete(final long id) {
		return new DeletePerson().execute(id);
	}

	@Override
	public void resetDatabase() {
		new DeleteAll().execute((Void) null);
	}

}
