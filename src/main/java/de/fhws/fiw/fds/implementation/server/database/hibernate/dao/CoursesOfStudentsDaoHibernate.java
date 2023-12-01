package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseRelationAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface CoursesOfStudentsDaoHibernate extends IDatabaseRelationAccessObjectHibernate<CourseDB> {
    CollectionModelHibernateResult<CourseDB> readByQuery(long primaryId, String name);
}
