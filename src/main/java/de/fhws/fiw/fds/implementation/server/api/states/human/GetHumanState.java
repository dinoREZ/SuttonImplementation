package de.fhws.fiw.fds.implementation.server.api.states.human;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetHumanState extends AbstractGetState<Human> {


    public GetHumanState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Human> loadModel() {
        return DaoFactory.getInstance().getHumanDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetHumanState( this );
        }
    }
}
