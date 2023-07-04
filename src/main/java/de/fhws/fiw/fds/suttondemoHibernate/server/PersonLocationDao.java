package de.fhws.fiw.fds.suttondemoHibernate.server;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;

public interface PersonLocationDao extends IDatabaseRelationAccessObject<Location> {

    void initializeDatabase();

    void resetDatabase();

}
