package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course.*;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class CourseDaoHibernateImpl implements CourseDaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    public CourseDaoHibernateImpl() {
        super();
    }

    @Override
    public CollectionModelHibernateResult<CourseDB> readByQuery(String name, SearchParameter searchParameter) {
        return new CourseByQueryOperation(emf, name, searchParameter).start();
    }

    @Override
    public NoContentResult create(CourseDB model) {
        return new CourseCreateOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<CourseDB> readById(long id) {
        return new CourseByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<CourseDB> readAll(SearchParameter searchParameter) {
        // TODO?
        return null;
    }

    @Override
    public NoContentResult update(CourseDB model) {
        return new CourseUpdateOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new CourseDeleteOperation(emf, id).start();
    }
}
