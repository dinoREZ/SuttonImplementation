package de.fhws.fiw.fds.sutton.server.database.hibernate.dao;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

import java.util.function.Predicate;

public interface IDatabaseRelationAccessObjectHibernate<B extends AbstractDBModel> {

    NoContentResult create(final long primaryId, final B secondaryModel);

    NoContentResult update(final long primaryId, final B secondaryModel);

    NoContentResult deleteRelation(final long primaryId, final long secondaryId);

    NoContentResult deleteRelationsFromPrimary(final long primaryId);

    NoContentResult deleteRelationsToSecondary(final long secondaryId);

    SingleModelHibernateResult<B> readById(final long primaryId, final long secondaryId);

    CollectionModelHibernateResult<B> readByPredicate(final long primaryId, final Predicate<B> predicate);

    CollectionModelHibernateResult<B> readAllByPredicate(final long primaryId, final Predicate<B> predicate);

}
