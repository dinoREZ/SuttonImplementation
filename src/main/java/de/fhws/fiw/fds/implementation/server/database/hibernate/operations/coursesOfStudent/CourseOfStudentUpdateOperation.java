package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractUpdateRelationByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class CourseOfStudentUpdateOperation extends AbstractUpdateRelationByPrimaryIdOperation<StudentDB, CourseDB, StudentCourseDB> {
    public CourseOfStudentUpdateOperation(EntityManagerFactory emf, long primaryId, CourseDB secondaryModelToUpdate) {
        super(emf, StudentCourseDB.class, StudentDB.class, primaryId, secondaryModelToUpdate);
    }
}
