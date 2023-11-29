package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;

public class StudentByQueryOperation extends AbstractDatabaseOperation<StudentDB, CollectionModelHibernateResult<StudentDB>> {

    private String firstName;
    private String lastName;

    public StudentByQueryOperation(EntityManagerFactory emf, String firstName, String lastName) {
        super(emf);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<StudentDB> root) {
        final Predicate matchFirstName = criteriaBuilder.like(root.get("firstName"), "%" + this.firstName + "%");
        final Predicate matchLastName = criteriaBuilder.like(root.get("lastName"), "%" + this.lastName + "%");

        return criteriaBuilder.and(matchFirstName, matchLastName);
    }

    @Override
    protected CollectionModelHibernateResult<StudentDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StudentDB> criteriaQuery = criteriaBuilder.createQuery(StudentDB.class);
        final Root<StudentDB> root = criteriaQuery.from(StudentDB.class);

        final Order sorting = criteriaBuilder.asc(root.get("lastName"));

        criteriaQuery = criteriaQuery.select(root).where(formulateConditions(criteriaBuilder, root)).orderBy(sorting);
        final TypedQuery<StudentDB> allQuery = em.createQuery(criteriaQuery);

        CollectionModelHibernateResult<StudentDB> returnValue = new CollectionModelHibernateResult<>(allQuery.getResultList());
        returnValue.setTotalNumberOfResult(getTotalResultCount());

        return returnValue;
    }

    private int getTotalResultCount() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<StudentDB> root = countQuery.from(StudentDB.class);

        countQuery.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root));
        return em
                .createQuery(countQuery)
                .getSingleResult()
                .intValue();
    }

    @Override
    protected CollectionModelHibernateResult<StudentDB> errorResult() {
        return null;
    }
}
