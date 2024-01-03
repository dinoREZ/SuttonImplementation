package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent.*;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse.StudentsOfCourseDeleteByIdOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class CoursesOfStudentsDaoHibernateImpl implements CoursesOfStudentsDaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    @Override
    public CollectionModelHibernateResult<CourseDB> readByQuery(long primaryId, String name, SearchParameter searchParameter) {
        return new CoursesOfStudentByQueryOperation(emf, primaryId, name, searchParameter).start();
    }

    @Override
    public NoContentResult create(long primaryId, CourseDB secondaryModel) {
        System.out.println(primaryId + " " + secondaryModel.getId());
        return new CoursesOfStudentsCreateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult update(long primaryId, CourseDB secondaryModel) {
        return new CourseOfStudentUpdateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return new CoursesOfStudentsDeleteOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return new StudentsOfCourseDeleteByIdOperation(emf, primaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return new CoursesOfStudentDeleteByIdOperation(emf, secondaryId).start();
    }

    @Override
    public SingleModelHibernateResult<CourseDB> readById(long primaryId, long secondaryId) {
        return new CourseOfStudentByIdOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public CollectionModelHibernateResult<CourseDB> readAll(long primaryId, SearchParameter searchParameter) {
        return null;
    }
}
