package de.fhws.fiw.fds.implementation.server.api.states.human;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutHumanState extends AbstractPutState<Human> {
    protected PutHumanState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Human> loadModel() {
        return DaoFactory.getInstance().getHumanDao().readById(requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getHumanDao().update(modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractPutStateBuilder<Human> {
        @Override
        public PutHumanState build() {
            return new PutHumanState(this);
        }
    }
}
