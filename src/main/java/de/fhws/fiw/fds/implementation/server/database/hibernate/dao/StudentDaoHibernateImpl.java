package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentDaoHibernateImpl implements StudentDaoHibernate {

    private static final String PERSISTENCE_UNIT_NAME = "de.fhws.fiw.fds.sutton";
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public StudentDaoHibernateImpl() {
        super();
        populateDatabase();
    }

    @Override
    public NoContentResult create(StudentDB model) {
        return new StudentCreateOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<StudentDB> readById(long id) {
        return new StudentByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<StudentDB> readAll(SearchParameter searchParameter) {
        // TODO?
        return new CollectionModelHibernateResult<>();
    }

    @Override
    public CollectionModelHibernateResult<StudentDB> readByQuery(String firstName, String lastName) {
        return new StudentByQueryOperation(emf, firstName, lastName).start();
    }

    @Override
    public NoContentResult update(StudentDB model) {
        return new StudentUpdateOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new StudentDeleteOperation(emf, id).start();
    }

    private void populateDatabase() {
        StudentDB student = new StudentDB();
        student.setFirstName("David");
        student.setLastName("Ruppert");

        create(student);
    }
}
