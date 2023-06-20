package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractPersistOperation;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class PersistPersonOperation extends AbstractPersistOperation<PersonDB> {

    public PersistPersonOperation(EntityManagerFactory emf, PersonDB modelToPersist) {
        super(emf, modelToPersist);
    }

}
