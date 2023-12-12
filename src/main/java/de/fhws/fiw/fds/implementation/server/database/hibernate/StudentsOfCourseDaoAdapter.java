package de.fhws.fiw.fds.implementation.server.database.hibernate;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.StudentsOfCourseDao;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.StudentsOfCourseDaoHibernate;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.StudentsOfCourseDaoHibernateImpl;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
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
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(student -> createFrom(student, courseId)).collect(Collectors.toList()));
        }
        return returnValue;
    }

    @Override
    public NoContentResult create(long primaryId, Student secondary) {
        return dao.create(primaryId, createFrom(secondary));
    }

    @Override
    public NoContentResult update(long primaryId, Student secondary) {
        return dao.update(primaryId, createFrom(secondary));
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return dao.deleteRelation(primaryId, secondaryId);
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return dao.deleteRelationsFromPrimary(primaryId);
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return dao.deleteRelationsToSecondary(secondaryId);
    }

    @Override
    public SingleModelResult<Student> readById(long primaryId, long secondaryId) {
        SingleModelHibernateResult<StudentDB> result = dao.readById(primaryId, secondaryId);

        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            SingleModelResult<Student> returnValue = new SingleModelResult<>();
            returnValue.setError();
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult(), primaryId));
        }
    }

    @Override
    public CollectionModelResult<Student> readAll(long primaryId, SearchParameter searchParameter) {
        return null;
    }

    private Student createFrom(StudentDB studentDB, long courseId) {
        final Student student = new Student();
        student.setPrimaryId(courseId);
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
