package de.fhws.fiw.fds.sutton.server.database.hibernate.models;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractDbRelation<A extends AbstractDBModel, B extends AbstractDBModel> {

    @EmbeddedId
    private DbRelationId dbRelationId = new DbRelationId();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("firstModelId")
    private A firstModel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("secondModelId")
    private B secondModel;

    public AbstractDbRelation() {
        // make JPA happy
        // also used for AbstractDBRelationOperations. Must be public.
    }

    public DbRelationId getDbRelationId() {
        return dbRelationId;
    }

    public void setDbRelationId(DbRelationId dbRelationId) {
        this.dbRelationId = dbRelationId;
    }

    public A getFirstModel() {
        return firstModel;
    }

    public void setFirstModel(A firstModel) {
        this.firstModel = firstModel;
    }

    public B getSecondModel() {
        return secondModel;
    }

    public void setSecondModel(B secondModel) {
        this.secondModel = secondModel;
    }

    @Embeddable
    public static class DbRelationId implements Serializable {

        @Column(name = "firstModelId")
        private long firstModelId;

        @Column(name = "secondModelId")
        private long secondModelId;

        public long getFirstModelId() {
            return firstModelId;
        }

        public void setFirstModelId(long firstModelId) {
            this.firstModelId = firstModelId;
        }

        public long getSecondModelId() {
            return secondModelId;
        }

        public void setSecondModelId(long secondModelId) {
            this.secondModelId = secondModelId;
        }
    }

}
