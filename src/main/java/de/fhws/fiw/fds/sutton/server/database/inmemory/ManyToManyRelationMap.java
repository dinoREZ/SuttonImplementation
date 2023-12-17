package de.fhws.fiw.fds.sutton.server.database.inmemory;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Collection;

public class ManyToManyRelationMap {
    private final MultiValuedMap<Long, Long> storage;

    public ManyToManyRelationMap() {
        this.storage = new HashSetValuedHashMap<>();
    }

    public void createRelation(long a, long b) {
        storage.put(a, b);
    }

    public Collection<Long> getAs(long b) {
        return invert().get(b);
    }

    public Collection<Long> getBs(long a) {
        return storage.get(a);
    }

    public boolean containsRelation(long a, long b) {
        return storage.entries().stream().anyMatch(entry -> entry.getKey() == a && entry.getValue() == b);
    }

    public boolean removeRelation(long a, long b) {
        return storage.removeMapping(a, b);
    }

    public void deleteRelationsFromA(long a) {
        storage.get(a).forEach(b -> removeRelation(a, b));
    }

    public void deleteRelationsFromB(long b) {
        storage.entries()
                .stream()
                .filter(entry -> entry.getValue() == b)
                .forEach(entry -> removeRelation(entry.getKey(), entry.getValue()));
    }

    private MultiValuedMap<Long, Long> invert() {
        HashSetValuedHashMap<Long, Long> returnValue = new HashSetValuedHashMap<>();
        storage.entries().forEach(entry -> returnValue.put(entry.getValue(), entry.getKey()));

        return returnValue;
    }
}
