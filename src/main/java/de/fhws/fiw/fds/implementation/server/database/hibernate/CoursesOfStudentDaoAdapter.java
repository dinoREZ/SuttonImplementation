package de.fhws.fiw.fds.implementation.server.database.hibernate;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.CoursesOfStudentDao;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.*;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.stream.Collectors;

public class CoursesOfStudentDaoAdapter implements CoursesOfStudentDao {
    CoursesOfStudentsDaoHibernate dao = new CoursesOfStudentsDaoHibernateImpl();

    public CoursesOfStudentDaoAdapter() {
        super();
    }

    @Override
    public CollectionModelResult<Course> readByQuery(long primaryId, String name, SearchParameter searchParameter) {
        CollectionModelHibernateResult<CourseDB> result = this.dao.readByQuery(primaryId, name, searchParameter);

        CollectionModelResult<Course> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();
        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(course -> createFrom(course, primaryId)).collect(Collectors.toList()));
        }
        return returnValue;
    }

    @Override
    public NoContentResult create(long primaryId, Course secondary) {
        CourseDB courseDB = createFrom(secondary);
        NoContentResult returnValue = this.dao.create(primaryId, courseDB);
        secondary.setId(courseDB.getId());
        return returnValue;
    }

    @Override
    public NoContentResult update(long primaryId, Course secondary) {
        CourseDB courseDB = createFrom(secondary);
        NoContentResult returnValue = this.dao.update(primaryId, courseDB);
        secondary.setId(courseDB.getId());
        return returnValue;
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return this.dao.deleteRelation(primaryId, secondaryId);
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return this.dao.deleteRelationsFromPrimary(primaryId);
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return this.dao.deleteRelationsToSecondary(secondaryId);
    }

    @Override
    public SingleModelResult<Course> readById(long primaryId, long secondaryId) {
        SingleModelHibernateResult<CourseDB> result = this.dao.readById(primaryId, secondaryId);

        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            SingleModelResult<Course> returnValue = new SingleModelResult<>();
            returnValue.setError();
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult(), primaryId));
        }
    }

    @Override
    public CollectionModelResult<Course> readAll(long primaryId, SearchParameter searchParameter) {
        return null;
    }

    private Course createFrom(CourseDB courseDB, long studentId) {
        final Course course = new Course();
        course.setPrimaryId(studentId);
        course.setId(courseDB.getId());
        course.setName(courseDB.getName());
        return course;
    }

    private CourseDB createFrom(Course course) {
        final CourseDB courseDB = new CourseDB();
        courseDB.setId(course.getId());
        courseDB.setName(course.getName());
        return courseDB;
    }
}
