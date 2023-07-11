package de.fhws.fiw.fds.suttondemoHibernate.server;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Person;

public interface PersonLocationDao extends IDatabaseRelationAccessObject<Location> {

    CollectionModelResult<Location> readByCityName(long primaryId, String cityName, SearchParameter searchParameter);

    void initializeDatabase();

    void resetDatabase();

}
