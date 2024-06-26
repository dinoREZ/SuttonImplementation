package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface CoursesOfStudentDao extends IDatabaseRelationAccessObject<Course> {
    CollectionModelResult<Course> readByQuery(long primaryId, String name, Integer roomNumber, SearchParameter searchParameter);
}
