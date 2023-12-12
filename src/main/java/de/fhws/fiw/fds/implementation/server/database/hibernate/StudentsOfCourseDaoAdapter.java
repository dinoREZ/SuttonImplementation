package de.fhws.fiw.fds.implementation.server.database.hibernate;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.StudentsOfCourseDao;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.StudentsOfCourseDaoHibernate;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.StudentsOfCourseDaoHibernateImpl;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.stream.Collectors;

public class StudentsOfCourseDaoAdapter implements StudentsOfCourseDao {
    StudentsOfCourseDaoHibernate dao = new StudentsOfCourseDaoHibernateImpl();

    @Override
    public CollectionModelResult<Student> readByQuery(long courseId, String name) {
        CollectionModelHibernateResult<StudentDB> result = this.dao.readByQuery(courseId, name);

        CollectionModelResult<Student> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();
        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(this::createFrom).collect(Collectors.toList()));
        }
        return returnValue;
    }

    @Override
    public NoContentResult create(long primaryId, Student secondary) {
        return dao.create(primaryId, createFrom(secondary));
    }

    @Override
    public NoContentResult update(long primaryId, Student secondary) {
        return null;
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return null;
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return null;
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return null;
    }

    @Override
    public SingleModelResult<Student> readById(long primaryId, long secondaryId) {
        return null;
    }

    @Override
    public CollectionModelResult<Student> readAll(long primaryId, SearchParameter searchParameter) {
        return null;
    }

    private Student createFrom(StudentDB studentDB) {
        final Student student = new Student();
        student.setId(studentDB.getId());
        student.setFirstName(studentDB.getFirstName());
        student.setLastName(studentDB.getLastName());
        return student;
    }

    private StudentDB createFrom(Student student) {
        final StudentDB studentDB = new StudentDB();
        studentDB.setId(student.getId());
        studentDB.setFirstName(student.getFirstName());
        studentDB.setLastName(student.getLastName());
        return studentDB;
    }
}
