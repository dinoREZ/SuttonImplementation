package de.fhws.fiw.fds.suttondemoHibernate.server;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;

public interface LocationDao extends IDatabaseAccessObject<Location> {

    void resetDatabase();

    void initializeDatabase();

}
