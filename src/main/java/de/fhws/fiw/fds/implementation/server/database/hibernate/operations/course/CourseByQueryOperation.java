package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CourseByQueryOperation extends AbstractDatabaseOperation<CourseDB, CollectionModelHibernateResult<CourseDB>> {

    private String name;
    private SearchParameter searchParameter;

    public CourseByQueryOperation(EntityManagerFactory emf, String name, SearchParameter searchParameter) {
        super(emf);
        this.name = name;
        this.searchParameter = searchParameter;
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<CourseDB> root) {
        final Predicate matchName = criteriaBuilder.like(root.get("name"), "%" + name + "%");

        return matchName;
    }

    @Override
    protected CollectionModelHibernateResult<CourseDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CollectionModelHibernateResult<CourseDB> returnValue = new CollectionModelHibernateResult<>(readResult());
        returnValue.setTotalNumberOfResult(getTotalResultCount());
        return returnValue;
    }

    private List<CourseDB> readResult() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<CourseDB> criteriaQuery = criteriaBuilder.createQuery(CourseDB.class);
        final Root<CourseDB> root = criteriaQuery.from(CourseDB.class);

        final Order sorting = criteriaBuilder.asc(root.get("name"));

        criteriaQuery = criteriaQuery.select(root).where(formulateConditions(criteriaBuilder, root)).orderBy(sorting);

        return em.createQuery(criteriaQuery)
                .setFirstResult(this.searchParameter.getOffset())
                .setMaxResults(this.searchParameter.getSize())
                .getResultList();
    }

    private int getTotalResultCount() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<CourseDB> root = countQuery.from(CourseDB.class);

        countQuery.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root));
        return this.em.createQuery(countQuery)
                .getSingleResult()
                .intValue();
    }

    @Override
    protected CollectionModelHibernateResult<CourseDB> errorResult() {
        final CollectionModelHibernateResult<CourseDB> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
