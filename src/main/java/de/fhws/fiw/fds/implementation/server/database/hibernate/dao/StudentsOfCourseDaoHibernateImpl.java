package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent.CoursesOfStudentDeleteByIdOperation;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent.CoursesOfStudentsDeleteOperation;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse.*;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class StudentsOfCourseDaoHibernateImpl implements StudentsOfCourseDaoHibernate {

    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    @Override
    public CollectionModelHibernateResult<StudentDB> readByQuery(long courseId, String firstName, SearchParameter searchParameter) {
        return new StudentsOfCourseByQueryOperation(emf, courseId, firstName, searchParameter).start();
    }

    @Override
    public NoContentResult create(long courseId, StudentDB secondaryModel) {
        return new StudentsOfCourseCreateOperation(emf, courseId, secondaryModel).start();
    }

    @Override
    public NoContentResult update(long primaryId, StudentDB secondaryModel) {
        return new StudentOfCourseUpdateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult deleteRelation(long courseId, long studentId) {
        return new CoursesOfStudentsDeleteOperation(emf, studentId, courseId).start();
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return new CoursesOfStudentDeleteByIdOperation(emf, primaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return new StudentsOfCourseDeleteByIdOperation(emf, secondaryId).start();
    }

    @Override
    public SingleModelHibernateResult<StudentDB> readById(long primaryId, long secondaryId) {
        return new StudentOfCourseByIdOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public CollectionModelHibernateResult<StudentDB> readAll(long primaryId, SearchParameter searchParameter) {
        return null;
    }
}
