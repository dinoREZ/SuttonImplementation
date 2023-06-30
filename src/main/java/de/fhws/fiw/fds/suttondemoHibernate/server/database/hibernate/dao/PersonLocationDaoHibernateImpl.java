package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.dao;

import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations.personLocation.LoadAllPersonLocationsOperation;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations.personLocation.PersistPersonLocationOperation;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.function.Predicate;

public class PersonLocationDaoHibernateImpl implements PersonLocationDaoHibernate{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.fhws.fiw.fds.suttondemoHibernate");

    public PersonLocationDaoHibernateImpl() {
        super();
    }

    @Override
    public NoContentResult create(long primaryId, LocationDB secondaryModel) {
        return new PersistPersonLocationOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public CollectionModelHibernateResult<LocationDB> readByPredicate(long primaryId, Predicate<LocationDB> predicate) {
        return new LoadAllPersonLocationsOperation(emf, primaryId, predicate).start();
    }

    // FIXME: wo ist der Unterschied zu oben?
    @Override
    public CollectionModelHibernateResult<LocationDB> readAllByPredicate(long primaryId, Predicate<LocationDB> predicate) {
        return readByPredicate(primaryId, predicate);
    }

}
