package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;

public class CourseByQueryOperation extends AbstractDatabaseOperation<CourseDB, CollectionModelHibernateResult<CourseDB>> {

    private String name;

    public CourseByQueryOperation(EntityManagerFactory emf, String name) {
        super(emf);
        this.name = name;
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<CourseDB> root) {
        final Predicate matchName = criteriaBuilder.like(root.get("name"), "%" + name + "%");

        return matchName;
    }

    @Override
    protected CollectionModelHibernateResult<CourseDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CourseDB> criteriaQuery = criteriaBuilder.createQuery(CourseDB.class);
        final Root<CourseDB> root = criteriaQuery.from(CourseDB.class);

        final Order sorting = criteriaBuilder.asc(root.get("name"));

        criteriaQuery = criteriaQuery.select(root).where(formulateConditions(criteriaBuilder, root)).orderBy(sorting);
        final TypedQuery<CourseDB> allQuery = em.createQuery(criteriaQuery);

        CollectionModelHibernateResult<CourseDB> returnValue = new CollectionModelHibernateResult<>(allQuery.getResultList());
        returnValue.setTotalNumberOfResult(getTotalResultCount());

        return returnValue;
    }

    private int getTotalResultCount() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<CourseDB> root = countQuery.from(CourseDB.class);

        countQuery.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root));
        return em
                .createQuery(countQuery)
                .getSingleResult()
                .intValue();
    }

    @Override
    protected CollectionModelHibernateResult<CourseDB> errorResult() {
        // TODO?
        return null;
    }
}
