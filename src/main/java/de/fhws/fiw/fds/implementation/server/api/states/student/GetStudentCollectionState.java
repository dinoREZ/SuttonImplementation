package de.fhws.fiw.fds.implementation.server.api.states.student;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class GetStudentCollectionState extends AbstractGetCollectionState<Student> {

    protected GetStudentCollectionState(Builder builder) {
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
        addLink(StudentUri.REL_PATH, StudentRelTypes.CREATE_STUDENT);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Student> {
        @Override
        public AbstractState build() {
            return new GetStudentCollectionState(this);
        }
    }
}
