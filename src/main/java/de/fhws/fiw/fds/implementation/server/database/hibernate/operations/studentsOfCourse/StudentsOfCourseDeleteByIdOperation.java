package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentsOfCourseDeleteByIdOperation extends AbstractDeleteAllRelationsByPrimaryIdOperation<StudentDB, CourseDB, StudentCourseDB> {
    public StudentsOfCourseDeleteByIdOperation(EntityManagerFactory emf, long primaryId) {
        super(emf, StudentCourseDB.class, primaryId);
    }
}
