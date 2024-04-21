package de.fhws.fiw.fds.implementation.server.database.inmemory;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.CourseDao;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class CourseDaoImpl extends AbstractInMemoryStorage<Course> implements CourseDao {
    @Override
    public CollectionModelResult<Course> readByQuery(String name, Integer roomNumber, SearchParameter searchParameter) {
        return readByPredicate(
                course -> (
                        course.getName().toLowerCase().contains(name.toLowerCase()) &&
                                (roomNumber == null || course.getRoomNumber() == roomNumber)
                ),
                searchParameter);
    }
}
