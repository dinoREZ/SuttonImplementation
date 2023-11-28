package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class HumanDeleteOperation extends AbstractDeleteByIdOperation<HumanDB> {
    public HumanDeleteOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, HumanDB.class, idToDelete);
    }
}
