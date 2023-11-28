package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HumanDaoHibernateImpl implements HumanDaoHibernate {

    private static final String PERSISTENCE_UNIT_NAME = "de.fhws.fiw.fds.sutton";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public HumanDaoHibernateImpl() {
        super();
        populateDatabase();
    }

    @Override
    public NoContentResult create(HumanDB model) {
        return new HumanCreateOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<HumanDB> readById(long id) {
        return new HumanByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<HumanDB> readAll(SearchParameter searchParameter) {
        // TODO?
        return new CollectionModelHibernateResult<>();
    }

    @Override
    public CollectionModelHibernateResult<HumanDB> readByQuery(String firstName, String lastName) {
        return new HumanByQueryOperation(emf, firstName, lastName).start();
    }

    @Override
    public NoContentResult update(HumanDB model) {
        return new HumanUpdateOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new HumanDeleteOperation(emf, id).start();
    }

    private void populateDatabase() {
        HumanDB human = new HumanDB();
        human.setFirstName("David");
        human.setLastName("Ruppert");

        create(human);
    }
}
