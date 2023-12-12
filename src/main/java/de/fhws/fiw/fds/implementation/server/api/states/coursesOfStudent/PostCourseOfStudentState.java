package de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostCourseOfStudentState extends AbstractPostRelationState<Course> {
    public PostCourseOfStudentState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getCoursesOfStudentDao().create(this.primaryId, this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractPostRelationStateBuilder<Course> {

        @Override
        public AbstractState build() {
            return new PostCourseOfStudentState(this);
        }
    }
}
