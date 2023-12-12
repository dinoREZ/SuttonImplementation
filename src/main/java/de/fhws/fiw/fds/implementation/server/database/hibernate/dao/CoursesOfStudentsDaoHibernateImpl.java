package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent.CourseOfStudentById;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent.CoursesOfStudentByQueryOperation;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent.CoursesOfStudentsCreateOperation;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class CoursesOfStudentsDaoHibernateImpl implements CoursesOfStudentsDaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    @Override
    public CollectionModelHibernateResult<CourseDB> readByQuery(long primaryId, String name) {
        return new CoursesOfStudentByQueryOperation(emf, primaryId, name).start();
    }

    @Override
    public NoContentResult create(long primaryId, CourseDB secondaryModel) {
        return new CoursesOfStudentsCreateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult update(long primaryId, CourseDB secondaryModel) {
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
    public SingleModelHibernateResult<CourseDB> readById(long primaryId, long secondaryId) {
        return new CourseOfStudentById(emf, primaryId, secondaryId).start();
    }

    @Override
    public CollectionModelHibernateResult<CourseDB> readAll(long primaryId, SearchParameter searchParameter) {
        return null;
    }
}
