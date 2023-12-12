package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteRelationByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class CoursesOfStudentsDeleteOperation extends AbstractDeleteRelationByIdOperation<StudentDB, CourseDB, StudentCourseDB> {
    public CoursesOfStudentsDeleteOperation(EntityManagerFactory emf, long primaryId, long secondaryId) {
        super(emf, StudentCourseDB.class, primaryId, secondaryId);
    }
}
