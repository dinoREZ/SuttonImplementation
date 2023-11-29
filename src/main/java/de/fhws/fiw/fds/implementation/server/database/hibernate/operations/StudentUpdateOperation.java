package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentUpdateOperation extends AbstractUpdateOperation<StudentDB> {
    public StudentUpdateOperation(EntityManagerFactory emf, StudentDB model) {
        super(emf, model);
    }
}
