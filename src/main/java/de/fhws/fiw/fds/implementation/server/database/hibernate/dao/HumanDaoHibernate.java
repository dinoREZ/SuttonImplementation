package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface HumanDaoHibernate extends IDatabaseAccessObjectHibernate<HumanDB> {

    CollectionModelHibernateResult<HumanDB> readByQuery(String firstName, String lastName);

}
