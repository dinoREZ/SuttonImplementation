package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class HumanByIdOperation extends AbstractReadByIdOperation<HumanDB> {
    public HumanByIdOperation(EntityManagerFactory emf, long id) {
        super(emf, HumanDB.class, id);
    }
}
