package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import jakarta.persistence.EntityManagerFactory;

public class CourseUpdateOperation extends AbstractUpdateOperation<CourseDB> {
    public CourseUpdateOperation(EntityManagerFactory emf, CourseDB modelToUpdate) {
        super(emf, modelToUpdate);
    }
}
