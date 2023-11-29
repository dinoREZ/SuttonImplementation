package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentDeleteOperation extends AbstractDeleteByIdOperation<StudentDB> {
    public StudentDeleteOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, StudentDB.class, idToDelete);
    }
}
