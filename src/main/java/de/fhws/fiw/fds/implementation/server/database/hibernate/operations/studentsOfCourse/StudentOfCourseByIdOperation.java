package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class StudentOfCourseByIdOperation extends AbstractDatabaseOperation<StudentDB, SingleModelHibernateResult<StudentDB>> {

    private final long courseId;
    private final long studentId;

    public StudentOfCourseByIdOperation(EntityManagerFactory emf, long courseId, long studentId) {
        super(emf);
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @Override
    protected SingleModelHibernateResult<StudentDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentCourseDB> find = cb.createQuery(StudentCourseDB.class);
        Root<StudentCourseDB> rootEntry = find.from(StudentCourseDB.class);

        Predicate primaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.studentId);
        Predicate secondaryIdEquals = cb.equal(rootEntry.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), this.courseId);
        find.where(primaryIdEquals, secondaryIdEquals);

        Optional<StudentCourseDB> result = em.createQuery(find).getResultStream().findFirst();
        return result.map(relation -> new SingleModelHibernateResult<>(relation.getPrimaryModel()))
                .orElseGet(SingleModelHibernateResult::new);
    }

    @Override
    protected SingleModelHibernateResult<StudentDB> errorResult() {
        final SingleModelHibernateResult<StudentDB> returnValue = new SingleModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
