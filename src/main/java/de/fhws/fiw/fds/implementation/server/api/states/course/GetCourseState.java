package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class GetCourseState extends AbstractGetState<Course> {

    public GetCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Course> loadModel() {
        return DaoFactory.getInstance().getCourseDao().readById(requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(CourseUri.REL_PATH_ID, CourseRelTypes.UPDATE_COURSE, this.requestedId);
        addLink(CourseUri.REL_PATH_ID, CourseRelTypes.DELETE_COURSE, this.requestedId);
    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetCourseState( this );
        }
    }
}
