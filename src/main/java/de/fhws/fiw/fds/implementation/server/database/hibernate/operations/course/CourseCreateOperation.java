package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class CourseCreateOperation extends AbstractPersistOperation<CourseDB> {
    public CourseCreateOperation(EntityManagerFactory emf, CourseDB modelToPersist) {
        super(emf, modelToPersist);
    }
}
