package de.fhws.fiw.fds.implementation.server.api.states.human;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;

import javax.ws.rs.core.GenericEntity;

public class GetHumanCollectionState extends AbstractGetCollectionState<Human> {

    protected GetHumanCollectionState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected void defineHttpResponseBody() {
        this.responseBuilder.entity(new GenericEntity<>(this.result.getResult()) {

        });
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Human> {
        @Override
        public AbstractState build() {
            return new GetHumanCollectionState(this);
        }
    }
}
