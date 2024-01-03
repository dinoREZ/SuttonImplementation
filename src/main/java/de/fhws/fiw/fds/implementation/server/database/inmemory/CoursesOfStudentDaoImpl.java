package de.fhws.fiw.fds.implementation.server.database.inmemory;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.CoursesOfStudentDao;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.implementation.server.database.StudentsOfCourseDao;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryManyToManyRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class CoursesOfStudentDaoImpl implements CoursesOfStudentDao {

    private final AbstractInMemoryManyToManyRelationStorage<Student, Course> relationStorage;

    public CoursesOfStudentDaoImpl(AbstractInMemoryManyToManyRelationStorage<Student, Course> relationStorage) {
        super();
        this.relationStorage = relationStorage;
    }

    @Override
    public CollectionModelResult<Course> readByQuery(long primaryId, String name, SearchParameter searchParameter) {
        // TODO: what about searchParameter
        return this.relationStorage.readBByPredicate(primaryId, course -> course.getName().toLowerCase().contains(name.toLowerCase()));
    }

    @Override
    public NoContentResult create(long primaryId, Course secondary) {
        return this.relationStorage.createB(primaryId, secondary);
    }

    @Override
    public NoContentResult update(long primaryId, Course secondary) {
        return this.relationStorage.updateB(primaryId, secondary);
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return this.relationStorage.deleteRelation(secondaryId, primaryId);
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return this.relationStorage.deleteRelationsFromA(primaryId);
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return this.relationStorage.deleteRelationsFromB(secondaryId);
    }

    @Override
    public SingleModelResult<Course> readById(long primaryId, long secondaryId) {
        return this.relationStorage.readBById(secondaryId, primaryId);
    }

    @Override
    public CollectionModelResult<Course> readAll(long primaryId, SearchParameter searchParameter) {
        // TODO: what about searchParameter
        return this.relationStorage.readBByPredicate(primaryId, course -> true);
    }
}
