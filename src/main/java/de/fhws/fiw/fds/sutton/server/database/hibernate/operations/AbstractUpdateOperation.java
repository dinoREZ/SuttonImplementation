package de.fhws.fiw.fds.sutton.server.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class AbstractUpdateOperation<T extends AbstractDBModel>
        extends AbstractDatabaseOperation<T, NoContentResult> {

    private T modelToUpdate;

    public AbstractUpdateOperation(EntityManagerFactory emf, T modelToUpdate) {
        super(emf);
        this.modelToUpdate = modelToUpdate;
    }

    @Override
    protected NoContentResult run() {
        this.em.merge(this.modelToUpdate);
        return new NoContentResult();
    }

    @Override
    protected NoContentResult errorResult() {
        final NoContentResult returnValue = new NoContentResult();
        returnValue.setError();
        return returnValue;
    }

}
