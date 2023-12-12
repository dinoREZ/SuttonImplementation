package de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;

import javax.ws.rs.core.GenericEntity;

public class GetCoursesOfStudentState extends AbstractGetCollectionRelationState<Course> {
    public GetCoursesOfStudentState(Builder builder) {
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
        addLink(CoursesOfStudentUri.REL_PATH, CoursesOfStudentRelTypes.CREATE_COURSE, primaryId);
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Course> {
        @Override
        public AbstractState build() {
            return new GetCoursesOfStudentState(this);
        }
    }
}
