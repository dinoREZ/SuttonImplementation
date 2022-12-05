package de.fhws.fiw.fds.suttondemo.database.dbms;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttondemo.database.PersonDao;
import de.fhws.fiw.fds.suttondemo.database.dbms.operations.*;
import de.fhws.fiw.fds.suttondemo.models.Person;

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
	public CollectionModelResult<Person> readByFirstNameAndLastName(final String firstName, final String lastName,
			SearchParameter searchParameter) {
		return new LoadPersonsByFirstNameAndLastName().execute(new FirstAndLastName(firstName, lastName));
	}

	@Override
	public NoContentResult update(final Person model) {
		return new UpdatePerson().execute(model);
	}

	@Override
	public NoContentResult delete(final long id) {
		return new DeletePerson().execute(id);
	}

}
