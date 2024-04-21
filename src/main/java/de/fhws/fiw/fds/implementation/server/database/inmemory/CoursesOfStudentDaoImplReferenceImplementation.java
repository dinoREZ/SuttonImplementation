package de.fhws.fiw.fds.implementation.server.database.inmemory;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.CoursesOfStudentDao;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;

public class CoursesOfStudentDaoImplReferenceImplementation extends AbstractInMemoryRelationStorage<Course> implements CoursesOfStudentDao {

    @Override
    public CollectionModelResult<Course> readByQuery(long primaryId, String name, Integer roomNumber, SearchParameter searchParameter) {
        return readByPredicate(primaryId, course -> (
            course.getName().contains(name) &&
                    (roomNumber == null || course.getRoomNumber() == roomNumber)
                ));
    }

    @Override
    public CollectionModelResult<Course> readAll(long primaryId, SearchParameter searchParameter) {
        return readByPredicate(primaryId, course -> true);
    }

    @Override
    protected IDatabaseAccessObject<Course> getSecondaryStorage() {
        return DaoFactory.getInstance().getCourseDao();
    }
}
