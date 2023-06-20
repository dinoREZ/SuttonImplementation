package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractReadAllOperation;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class LoadAllPersonsOperations extends AbstractReadAllOperation<PersonDB> {

    public LoadAllPersonsOperations(EntityManagerFactory emf) {
        super(emf, PersonDB.class);
    }

}
