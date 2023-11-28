package de.fhws.fiw.fds.implementation.server.api.states.human;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteHumanState extends AbstractDeleteState<Human> {

    public DeleteHumanState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Human> loadModel() {
        return DaoFactory.getInstance().getHumanDao().readById(modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getHumanDao().delete(modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractDeleteStateBuilder {
        @Override
        public DeleteHumanState build() {
            return new DeleteHumanState( this );
        }
    }
}
