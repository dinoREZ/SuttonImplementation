package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsOfCourseByQueryOperation extends AbstractDatabaseOperation<StudentDB, CollectionModelHibernateResult<StudentDB>> {

    private final long courseId;
    private final String firstName;

    public StudentsOfCourseByQueryOperation(EntityManagerFactory emf, long courseId, String firstName) {
        super(emf);
        this.courseId = courseId;
        this.firstName = firstName;
    }

    @Override
    protected CollectionModelHibernateResult<StudentDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CollectionModelHibernateResult<StudentDB> returnValue = new CollectionModelHibernateResult<>(readResults());
        returnValue.setTotalNumberOfResult(getTotalNumberOfResults());
        return returnValue;
    }

    @Override
    protected CollectionModelHibernateResult<StudentDB> errorResult() {
       final CollectionModelHibernateResult<StudentDB> returnValue = new CollectionModelHibernateResult<>();
       returnValue.setError();
       return returnValue;
    }

    private List<StudentDB> readResults() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StudentCourseDB> find = criteriaBuilder.createQuery(StudentCourseDB.class);
        Root<StudentCourseDB> root = find.from(StudentCourseDB.class);
        Join<StudentCourseDB, StudentDB> join = root.join(SuttonColumnConstants.PRIMARY_MODEL);

        find.where(courseIdEquals(criteriaBuilder, root), nameMatcher(criteriaBuilder, root, join));

        return this.em
                .createQuery(find)
                .getResultList()
                .stream()
                .map(AbstractDBRelation::getPrimaryModel)
                .collect(Collectors.toList());
    }

    private int getTotalNumberOfResults() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> find = criteriaBuilder.createQuery(Long.class);
        Root<StudentCourseDB> root = find.from(StudentCourseDB.class);
        Join<StudentCourseDB, StudentDB> join = root.join(SuttonColumnConstants.PRIMARY_MODEL);

        find.select(criteriaBuilder.count(root)).where(courseIdEquals(criteriaBuilder, root), nameMatcher(criteriaBuilder, root, join));

        return this.em
                .createQuery(find)
                .getSingleResult().intValue();
    }

    private Predicate courseIdEquals(CriteriaBuilder criteriaBuilder, Root<StudentCourseDB> root) {
        return criteriaBuilder.equal(root.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.SECONDARY_ID), this.courseId);
    }

    private Predicate nameMatcher(CriteriaBuilder criteriaBuilder, Root<StudentCourseDB> root, Join<StudentCourseDB, StudentDB> join) {
        return criteriaBuilder.like(join.get("firstName"), "%" + firstName + "%");
    }
}
