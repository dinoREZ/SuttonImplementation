package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class CourseByIdOperation extends AbstractReadByIdOperation<CourseDB> {
    public CourseByIdOperation(EntityManagerFactory emf, long idToLoad) {
        super(emf, CourseDB.class, idToLoad);
    }
}
