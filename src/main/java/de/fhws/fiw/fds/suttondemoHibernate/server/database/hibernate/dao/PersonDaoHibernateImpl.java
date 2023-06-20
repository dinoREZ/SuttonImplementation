package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.dao;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class PersonDaoHibernateImpl implements PersonDaoHibernate {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fhws.fiw.fds.suttondemoHibernate");

    public PersonDaoHibernateImpl() {
        super();
    }

    @Override
    public NoContentResult create(PersonDB model) {
        return new PersistPersonOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<PersonDB> readById(long id) {
        return new LoadPersonByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<PersonDB> readAll(SearchParameter searchParameter) {
        /* ATTENTION: parameter searchParameter is NOT used by intention. To be done by students later. */
        return new LoadAllPersonsOperations(emf).start();
    }

    @Override
    public CollectionModelHibernateResult<PersonDB> readByFirstNameAndLastName(String firstName, String lastName,
                                                                               SearchParameter searchParameter) {
        return new LoadAllPersonsByFirstAndLastName(emf, firstName, lastName, searchParameter).start();
    }

    @Override
    public NoContentResult update(PersonDB model) {
        return new UpdatePersonOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new DeletePersonByIdOperation(emf, id).start();
    }

}
