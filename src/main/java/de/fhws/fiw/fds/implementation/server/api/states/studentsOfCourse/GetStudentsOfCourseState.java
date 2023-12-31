package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

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
        addLink(StudentsOfCourseUri.REL_PATH, StudentsOfCourseRelTypes.CREATE_STUDENT, primaryId);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Student> {

        @Override
        public AbstractState build() {
            return new GetStudentsOfCourseState(this);
        }
    }
}
