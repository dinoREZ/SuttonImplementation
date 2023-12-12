package de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetCourseOfStudentState extends AbstractGetRelationState<Course> {
    public GetCourseOfStudentState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Course> loadModel() {
        return DaoFactory.getInstance().getCoursesOfStudentDao().readById(primaryId, requestedId);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractGetRelationStateBuilder {
        @Override
        public AbstractState build() {
            return new GetCourseOfStudentState(this);
        }
    }
}
