package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.InvocationTargetException;

public class StudentOfCourseUpdateOperation extends AbstractDatabaseOperation<StudentDB, NoContentResult> {
    private final long courseId;
    private final StudentDB studentDB;

    public StudentOfCourseUpdateOperation(EntityManagerFactory emf, long courseId, StudentDB studentDB) {
        super(emf);
        this.courseId = courseId;
        this.studentDB = studentDB;
    }

    @Override
    protected NoContentResult run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        em.merge(studentDB);

        // Only persist new relation, if there is none existent
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentCourseDB> find = cb.createQuery(StudentCourseDB.class);
        Root<StudentCourseDB> rootEntry = find.from(StudentCourseDB.class);

        Predicate primaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), studentDB.getId());
        Predicate secondaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), courseId);
        find.where(primaryIdEquals, secondaryIdEquals);

        StudentCourseDB relationOnDB = em.createQuery(find).getResultStream().findFirst().orElse(null);

        if(relationOnDB == null){
            CourseDB courseDB = this.em.find(CourseDB.class, courseId);
            StudentCourseDB relation = new StudentCourseDB();
            relation.setPrimaryModel(studentDB);
            relation.setSecondaryModel(courseDB);
            this.em.merge(relation); // merge is needed because of detached entity exception
        }

        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }
}
