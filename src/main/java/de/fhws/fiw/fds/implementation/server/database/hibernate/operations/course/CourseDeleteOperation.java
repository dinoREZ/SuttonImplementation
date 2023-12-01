package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class CourseDeleteOperation extends AbstractDeleteByIdOperation<CourseDB> {
    public CourseDeleteOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, CourseDB.class, idToDelete);
    }
}
