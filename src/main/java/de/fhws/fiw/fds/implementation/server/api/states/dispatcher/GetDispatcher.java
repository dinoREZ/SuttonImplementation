package de.fhws.fiw.fds.implementation.server.api.states.dispatcher;

import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;

public class GetDispatcher extends AbstractGetDispatcherState {

    protected GetDispatcher(Builder builder) {
        super(builder);
    }

    @Override
    protected void defineTransitionLinks() {
        // TODO
        addLink("test", "testType");
    }

    public static class Builder extends AbstractDispatcherStateBuilder {
        @Override
        public GetDispatcher build() {
            return new GetDispatcher( this );
        }
    }
}
