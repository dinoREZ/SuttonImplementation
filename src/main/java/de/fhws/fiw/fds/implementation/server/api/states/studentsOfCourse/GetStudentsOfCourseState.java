package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;

import javax.ws.rs.core.GenericEntity;

public class GetStudentsOfCourseState extends AbstractGetCollectionRelationState<Student> {
    public GetStudentsOfCourseState(Builder builder) {
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

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Student> {

        @Override
        public AbstractState build() {
            return new GetStudentsOfCourseState(this);
        }
    }
}
