package de.fhws.fiw.fds.sutton.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.apache.commons.lang.ObjectUtils;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractInMemoryManyToManyRelationStorage<A extends AbstractModel, B extends AbstractModel> {
    protected ManyToManyRelationMap storageBtoA;

    protected AbstractInMemoryManyToManyRelationStorage() {
        this.storageBtoA = new ManyToManyRelationMap();
    }

    public NoContentResult createA(final long idOfB, final A a) {
        getAStorage().create(a);
        this.storageBtoA.createRelation(a.getId(), idOfB);
        return new NoContentResult();
    }

    public NoContentResult updateA(final long idOfB, final A a) {
        getAStorage().update(a);

        if (this.storageBtoA.containsRelation(a.getId(), idOfB) == false) {
            this.storageBtoA.createRelation(a.getId(), idOfB);
        }

        return new NoContentResult();
    }

    public NoContentResult deleteRelation(final long idOfB, final long idOfA) {
        if (this.storageBtoA.containsRelation(idOfA, idOfB)) {
            this.storageBtoA.removeRelation(idOfA, idOfB);
            return new NoContentResult();
        } else {
            return noMappingError();
        }
    }

    public NoContentResult deleteRelationsFromB(final long idOfB) {
        this.storageBtoA.deleteRelationsFromB(idOfB);
        return new NoContentResult();
    }

    public NoContentResult deleteRelationsFromA(final long idOfA) {
        this.storageBtoA.deleteRelationsFromA(idOfA);
        return new NoContentResult();
    }

    public SingleModelResult<A> readAById(final long idOfB, final long idOfA) {
        if (this.storageBtoA.containsRelation(idOfA, idOfB)) {
            return cloneA(idOfB, getAStorage().readById(idOfA));
        } else {
            return new SingleModelResult<>();
        }
    }

    protected CollectionModelResult<A> readAByPredicate(final long idOfB, final Predicate<A> predicate) {
        return new CollectionModelResult<>(
                cloneA(idOfB, this.storageBtoA.getAs(idOfB)
                        .stream()
                        .map(secondaryId -> loadA(secondaryId))
                        .filter(result -> result.isEmpty() == false)
                        .map(result -> result.getResult())
                        .filter(predicate)
                        .collect(Collectors.toList())));
    }

    protected CollectionModelResult<A> readAllAByPredicate(final long idOfB, final Predicate<A> predicate) {
        return new CollectionModelResult<>(
                cloneA(idOfB, this.getAStorage().readAll().getResult().stream()
                        .filter(predicate)
                        .collect(Collectors.toList())));
    }

    protected abstract IDatabaseAccessObject<A> getAStorage();

    private SingleModelResult<A> loadA(final long id) {
        return getAStorage().readById(id);
    }

    private NoContentResult noMappingError() {
        final NoContentResult errorResult = new NoContentResult();
        errorResult.setError(1, "No mapping between resources");
        return errorResult;
    }

    private SingleModelResult<A> cloneA(final long idOfB, final SingleModelResult<A> result) {
        return new SingleModelResult<>(cloneA(idOfB, result.getResult()));
    }

    private CollectionModelResult<A> cloneA(final long idOfB, final CollectionModelResult<A> result) {
        return new CollectionModelResult<>(cloneA(idOfB, result.getResult()));
    }

    private Collection<A> cloneA(final long idOfB, final Collection<A> result) {
        return result.stream().map(e -> cloneA(idOfB, e)).collect(Collectors.toList());
    }

    private A cloneA(final long idOfB, final A result) {
        final A clone = (A) ObjectUtils.cloneIfPossible(result);
        clone.setPrimaryId(idOfB);
        return clone;
    }




    public NoContentResult createB(final long idOfA, final B b) {
        getBStorage().create(b);
        this.storageBtoA.createRelation(idOfA, b.getId());
        return new NoContentResult();
    }

    public NoContentResult updateB(final long idOfA, final B b) {
        getBStorage().update(b);

        if (this.storageBtoA.containsRelation(idOfA, b.getId()) == false) {
            this.storageBtoA.createRelation(idOfA, b.getId());
        }

        return new NoContentResult();
    }

    public SingleModelResult<B> readBById(final long idOfB, final long idOfA) {
        if (this.storageBtoA.containsRelation(idOfA, idOfB)) {
            return cloneB(idOfA, getBStorage().readById(idOfB));
        } else {
            return new SingleModelResult<>();
        }
    }

    public CollectionModelResult<B> readBByPredicate(final long idOfA, final Predicate<B> predicate) {
        return new CollectionModelResult<>(
                cloneB(idOfA, this.storageBtoA.getBs(idOfA)
                        .stream()
                        .map(idOfB -> loadB(idOfB))
                        .filter(result -> result.isEmpty() == false)
                        .map(result -> result.getResult())
                        .filter(predicate)
                        .collect(Collectors.toList())));
    }

    public CollectionModelResult<B> readAllBByPredicate(final long idOfA, final Predicate<B> predicate) {
        return new CollectionModelResult<>(
                cloneB(idOfA, this.getBStorage().readAll().getResult().stream()
                        .filter(predicate)
                        .collect(Collectors.toList())));
    }

    protected abstract IDatabaseAccessObject<B> getBStorage();

    private SingleModelResult<B> loadB(final long id) {
        return getBStorage().readById(id);
    }

    private SingleModelResult<B> cloneB(final long idOfA, final SingleModelResult<B> result) {
        return new SingleModelResult<>(cloneB(idOfA, result.getResult()));
    }

    private CollectionModelResult<B> cloneB(final long idOfA, final CollectionModelResult<B> result) {
        return new CollectionModelResult<>(cloneB(idOfA, result.getResult()));
    }

    private Collection<B> cloneB(final long idOfA, final Collection<B> result) {
        return result.stream().map(e -> cloneB(idOfA, e)).collect(Collectors.toList());
    }

    private B cloneB(final long idOfA, final B result) {
        final B clone = (B) ObjectUtils.cloneIfPossible(result);
        clone.setPrimaryId(idOfA);
        return clone;
    }
}
