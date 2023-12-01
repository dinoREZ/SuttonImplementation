package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostCourseState extends AbstractPostState<Course> {
    protected PostCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getCourseDao().create(modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractPostStateBuilder<Course> {
        @Override
        public AbstractState build() {
            return new PostCourseState(this);
        }
    }
}
