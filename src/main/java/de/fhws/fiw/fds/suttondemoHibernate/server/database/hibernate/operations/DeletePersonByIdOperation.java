package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDeleteOperationById;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class DeletePersonByIdOperation extends AbstractDeleteOperationById<PersonDB> {

    public DeletePersonByIdOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, PersonDB.class, idToDelete);
    }

}
