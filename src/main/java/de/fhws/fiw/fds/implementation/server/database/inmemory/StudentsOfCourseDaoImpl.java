package de.fhws.fiw.fds.implementation.server.database.inmemory;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.implementation.server.database.StudentsOfCourseDao;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryManyToManyRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class StudentsOfCourseDaoImpl extends AbstractInMemoryManyToManyRelationStorage<Student, Course> implements StudentsOfCourseDao {

    @Override
    public CollectionModelResult<Student> readByQuery(long courseId, String name, SearchParameter searchParameter) {
        // TODO: what about searchParameter
        return this.readAByPredicate(courseId, student -> student.getLastName().toLowerCase().contains(name.toLowerCase()));
    }

    @Override
    public NoContentResult create(long primaryId, Student secondary) {
        return this.createA(primaryId, secondary);
    }

    @Override
    public NoContentResult update(long primaryId, Student secondary) {
        return this.updateA(primaryId, secondary);
    }

    @Override
    protected IDatabaseAccessObject<Student> getAStorage() {
        return DaoFactory.getInstance().getStudentDao();
    }

    @Override
    protected IDatabaseAccessObject<Course> getBStorage() {
        return DaoFactory.getInstance().getCourseDao();
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return this.deleteRelationsFromB(primaryId);
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return this.deleteRelationsFromA(secondaryId);
    }

    @Override
    public SingleModelResult<Student> readById(long primaryId, long secondaryId) {
        return this.readAById(primaryId, secondaryId);
    }

    @Override
    public CollectionModelResult<Student> readAll(long primaryId, SearchParameter searchParameter) {
        // TODO: what about searchParameter
        return this.readAByPredicate(primaryId, (student) -> true);
    }
}
