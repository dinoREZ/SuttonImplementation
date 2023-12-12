package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseRelationAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface StudentsOfCourseDaoHibernate extends IDatabaseRelationAccessObjectHibernate<StudentDB> {
    CollectionModelHibernateResult<StudentDB> readByQuery(long courseId, String name);
}
