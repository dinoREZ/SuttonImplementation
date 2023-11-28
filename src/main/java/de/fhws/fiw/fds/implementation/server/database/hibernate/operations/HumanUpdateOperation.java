package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import jakarta.persistence.EntityManagerFactory;

public class HumanUpdateOperation extends AbstractUpdateOperation<HumanDB> {
    public HumanUpdateOperation(EntityManagerFactory emf, HumanDB model) {
        super(emf, model);
    }
}
