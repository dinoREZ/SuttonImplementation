package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface StudentsOfCourseDao extends IDatabaseRelationAccessObject<Student> {
    CollectionModelResult<Student> readByQuery(long courseId, String name);
}
