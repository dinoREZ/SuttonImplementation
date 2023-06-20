package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.dao;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDataAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;

public interface PersonDaoHibernate extends IDataAccessObjectHibernate<PersonDB> {

    CollectionModelHibernateResult<PersonDB> readByFirstNameAndLastName(String firstName, String lastName,
                                                                        SearchParameter searchParameter);

}
