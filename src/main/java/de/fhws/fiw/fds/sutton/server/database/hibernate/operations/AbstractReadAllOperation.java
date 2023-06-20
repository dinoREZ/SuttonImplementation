package de.fhws.fiw.fds.sutton.server.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class AbstractReadAllOperation<T extends AbstractDBModel>
        extends AbstractDatabaseOperation<T, CollectionModelHibernateResult<T>> {

    private Class<T> clazz;

    public AbstractReadAllOperation(EntityManagerFactory emf, Class<T> clazz) {
        super(emf);
        this.clazz = clazz;
    }

    @Override
    protected CollectionModelHibernateResult<T> run() {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(this.clazz);
        final Root<T> rootEntry = cq.from(this.clazz);
        final CriteriaQuery<T> all = cq.select(rootEntry).where();
        final TypedQuery<T> allQuery = em.createQuery(all);

        return new CollectionModelHibernateResult<>(allQuery.getResultList());
    }

    @Override
    protected CollectionModelHibernateResult<T> errorResult() {
        final CollectionModelHibernateResult<T> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }

}
