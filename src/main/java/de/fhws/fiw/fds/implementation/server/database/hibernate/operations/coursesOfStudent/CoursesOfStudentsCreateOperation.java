package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractPersistRelationByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class CoursesOfStudentsCreateOperation extends AbstractPersistRelationByPrimaryIdOperation<StudentDB, CourseDB, StudentCourseDB> {
    public CoursesOfStudentsCreateOperation(EntityManagerFactory emf, long primaryId, CourseDB courseDB) {
        super(emf, StudentCourseDB.class, StudentDB.class, primaryId, courseDB);
    }
}
