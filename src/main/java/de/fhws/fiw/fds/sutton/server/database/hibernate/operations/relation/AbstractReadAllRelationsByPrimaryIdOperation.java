package de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDbRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractReadAllRelationsByPrimaryIdOperation<
        PrimaryModel extends AbstractDBModel,
        SecondaryModel extends AbstractDBModel,
        Relation extends AbstractDbRelation>
        extends AbstractDatabaseOperation<SecondaryModel, CollectionModelHibernateResult<SecondaryModel>> {

    private final Class<Relation> clazzOfRelation;
    private final Class<PrimaryModel> clazzOfPrimaryModel;
    private final long primaryId;

    private final java.util.function.Predicate<SecondaryModel> filter;

    public AbstractReadAllRelationsByPrimaryIdOperation(EntityManagerFactory emf,
                                                        Class<Relation> clazzOfRelation,
                                                        Class<PrimaryModel> clazzOfPrimaryModel,
                                                        long primaryId,
                                                        java.util.function.Predicate<SecondaryModel> predicate) {
        super(emf);
        this.clazzOfRelation = clazzOfRelation;
        this.clazzOfPrimaryModel = clazzOfPrimaryModel;
        this.primaryId = primaryId;
        this.filter = predicate;
    }

    @Override
    protected CollectionModelHibernateResult<SecondaryModel> run() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Relation> find = cb.createQuery(this.clazzOfRelation);
        Root<Relation> rootEntry = find.from(this.clazzOfRelation);

        Predicate firstModelIdEquals = cb.equal(rootEntry.get("dbRelationId").get("firstModelId"), this.primaryId);
        find.where(firstModelIdEquals);
        TypedQuery<Relation> findQuery = em.createQuery(find);
        List<SecondaryModel> results = findQuery.getResultList().stream()
                .map(r -> (SecondaryModel) r.getSecondModel())
                .filter(this.filter)
                .collect(Collectors.toList());
        return new CollectionModelHibernateResult<>(results);
    }

    @Override
    protected CollectionModelHibernateResult<SecondaryModel> errorResult() {
        final CollectionModelHibernateResult<SecondaryModel> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}
