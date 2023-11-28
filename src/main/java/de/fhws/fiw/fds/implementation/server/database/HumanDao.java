package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface HumanDao extends IDatabaseAccessObject<Human> {

    CollectionModelResult<Human> readByQuery(String firstName, String lastName);

}
