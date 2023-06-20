package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractUpdateOperation;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class UpdatePersonOperation extends AbstractUpdateOperation<PersonDB> {

    public UpdatePersonOperation(EntityManagerFactory emf, PersonDB modelToUpdate) {
        super(emf, modelToUpdate);
    }

}
