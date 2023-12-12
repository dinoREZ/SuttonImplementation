package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse.StudentOfCourseByIdOperation;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse.StudentsOfCourseByQueryOperation;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse.StudentsOfCourseCreateOperation;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class StudentsOfCourseDaoHibernateImpl implements StudentsOfCourseDaoHibernate {

    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    @Override
    public CollectionModelHibernateResult<StudentDB> readByQuery(long courseId, String firstName) {
        return new StudentsOfCourseByQueryOperation(emf, courseId, firstName).start();
    }

    @Override
    public NoContentResult create(long courseId, StudentDB secondaryModel) {
        return new StudentsOfCourseCreateOperation(emf, courseId, secondaryModel).start();
    }

    @Override
    public NoContentResult update(long primaryId, StudentDB secondaryModel) {
        return null;
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return null;
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return null;
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return null;
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
