package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

import java.lang.reflect.InvocationTargetException;

public class StudentsOfCourseCreateOperation extends AbstractDatabaseOperation<StudentDB, NoContentResult> {

    private final long courseId;
    private final StudentDB studentDB;

    public StudentsOfCourseCreateOperation(EntityManagerFactory emf, long courseId, StudentDB studentDB) {
        super(emf);
        this.courseId = courseId;
        this.studentDB = studentDB;
    }

    @Override
    protected NoContentResult run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CourseDB courseDB = this.em.find(CourseDB.class, courseId);
        this.em.persist(studentDB);
        StudentCourseDB relation = new StudentCourseDB();
        relation.setPrimaryModel(studentDB);
        relation.setSecondaryModel(courseDB);
        this.em.merge(relation);
        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }
}
