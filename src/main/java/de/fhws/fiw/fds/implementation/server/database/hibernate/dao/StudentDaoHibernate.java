package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface StudentDaoHibernate extends IDatabaseAccessObjectHibernate<StudentDB> {

    CollectionModelHibernateResult<StudentDB> readByQuery(String firstName, String lastName);

}
