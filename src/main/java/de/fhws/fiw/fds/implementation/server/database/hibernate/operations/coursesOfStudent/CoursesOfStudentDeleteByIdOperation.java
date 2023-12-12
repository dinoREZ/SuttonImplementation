package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsBySecondaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class CoursesOfStudentDeleteByIdOperation extends AbstractDeleteAllRelationsBySecondaryIdOperation<StudentDB, CourseDB, StudentCourseDB> {
    public CoursesOfStudentDeleteByIdOperation(EntityManagerFactory emf, long secondaryId) {
        super(emf, StudentCourseDB.class, secondaryId);
    }
}
