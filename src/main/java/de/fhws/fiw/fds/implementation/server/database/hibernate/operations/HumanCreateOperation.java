package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class HumanCreateOperation extends AbstractPersistOperation<HumanDB> {
    public HumanCreateOperation(EntityManagerFactory emf, HumanDB model) {
        super(emf, model);
    }
}
