package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;

import javax.ws.rs.core.GenericEntity;

public class GetCourseCollectionState extends AbstractGetCollectionState<Course> {
    protected GetCourseCollectionState(Builder builder) {
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
        addLink(CourseUri.REL_PATH, CourseRelTypes.CREATE_COURSE);
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Course> {
        @Override
        public AbstractState build() {
            return new GetCourseCollectionState(this);
        }
    }
}
