package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface StudentDao extends IDatabaseAccessObject<Student> {

    CollectionModelResult<Student> readByQuery(String firstName, String lastName, SearchParameter searchParameter);

}
