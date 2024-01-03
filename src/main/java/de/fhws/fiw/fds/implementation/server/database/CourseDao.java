package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface CourseDao extends IDatabaseAccessObject<Course> {
    CollectionModelResult<Course> readByQuery(String name, SearchParameter searchParameter);
}
