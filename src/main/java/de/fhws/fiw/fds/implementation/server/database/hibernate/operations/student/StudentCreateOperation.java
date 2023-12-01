package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.student;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentCreateOperation extends AbstractPersistOperation<StudentDB> {
    public StudentCreateOperation(EntityManagerFactory emf, StudentDB model) {
        super(emf, model);
    }
}
