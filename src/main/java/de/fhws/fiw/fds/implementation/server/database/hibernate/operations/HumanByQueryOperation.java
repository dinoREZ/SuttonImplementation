package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;

public class HumanByQueryOperation extends AbstractDatabaseOperation<HumanDB, CollectionModelHibernateResult<HumanDB>> {

    private String firstName;
    private String lastName;

    public HumanByQueryOperation(EntityManagerFactory emf, String firstName, String lastName) {
        super(emf);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<HumanDB> root) {
        final Predicate matchFirstName = criteriaBuilder.like(root.get("firstName"), "%" + this.firstName + "%");
        final Predicate matchLastName = criteriaBuilder.like(root.get("lastName"), "%" + this.lastName + "%");

        return criteriaBuilder.and(matchFirstName, matchLastName);
    }

    @Override
    protected CollectionModelHibernateResult<HumanDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<HumanDB> criteriaQuery = criteriaBuilder.createQuery(HumanDB.class);
        final Root<HumanDB> root = criteriaQuery.from(HumanDB.class);

        final Order sorting = criteriaBuilder.asc(root.get("lastName"));

        criteriaQuery = criteriaQuery.select(root).where(formulateConditions(criteriaBuilder, root)).orderBy(sorting);
        final TypedQuery<HumanDB> allQuery = em.createQuery(criteriaQuery);

        CollectionModelHibernateResult<HumanDB> returnValue = new CollectionModelHibernateResult<>(allQuery.getResultList());
        returnValue.setTotalNumberOfResult(getTotalResultCount());

        return returnValue;
    }

    private int getTotalResultCount() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<HumanDB> root = countQuery.from(HumanDB.class);

        countQuery.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root));
        return em
                .createQuery(countQuery)
                .getSingleResult()
                .intValue();
    }

    @Override
    protected CollectionModelHibernateResult<HumanDB> errorResult() {
        return null;
    }
}
