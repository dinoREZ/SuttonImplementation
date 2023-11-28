package de.fhws.fiw.fds.implementation.server.api.states.human;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostHumanState extends AbstractPostState<Human> {
    protected PostHumanState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getHumanDao().create(modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractPostStateBuilder<Human> {
        @Override
        public PostHumanState build() {
            return new PostHumanState(this);
        }
    }
}
