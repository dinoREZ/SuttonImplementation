package de.fhws.fiw.fds.implementation.server.database.hibernate;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.CourseDao;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.CourseDaoHibernate;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.CourseDaoHibernateImpl;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.stream.Collectors;

public class CourseDaoAdapter implements CourseDao {
    private final CourseDaoHibernate dao = new CourseDaoHibernateImpl();

    @Override
    public CollectionModelResult<Course> readByQuery(String name, SearchParameter searchParameter) {
        CollectionModelHibernateResult<CourseDB> result = dao.readByQuery(name, searchParameter);
        CollectionModelResult<Course> returnValue;

        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();
        } else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(this::createFrom).collect(Collectors.toList()));
        }

        return returnValue;
    }

    @Override
    public NoContentResult create(Course model) {
        CourseDB courseDB = createFrom(model);
        NoContentResult result = dao.create(courseDB);
        model.setId(courseDB.getId());
        return result;
    }

    @Override
    public SingleModelResult<Course> readById(long id) {
        SingleModelHibernateResult<CourseDB> result = dao.readById(id);
        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            SingleModelResult<Course> returnValue = new SingleModelResult<>();
            returnValue.setError();
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult()));
        }
    }

    @Override
    public CollectionModelResult<Course> readAll(SearchParameter searchParameter) {
        // TODO?
        return null;
    }

    @Override
    public NoContentResult update(Course model) {
        return dao.update(createFrom(model));
    }

    @Override
    public NoContentResult delete(long id) {
        return dao.delete(id);
    }

    private Course createFrom(CourseDB courseDB) {
        final Course course = new Course();
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
