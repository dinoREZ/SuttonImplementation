package de.fhws.fiw.fds.implementation.server.api.states.dispatcher;

import de.fhws.fiw.fds.implementation.server.api.states.course.CourseRelTypes;
import de.fhws.fiw.fds.implementation.server.api.states.course.CourseUri;
import de.fhws.fiw.fds.implementation.server.api.states.student.StudentRelTypes;
import de.fhws.fiw.fds.implementation.server.api.states.student.StudentUri;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class GetDispatcher extends AbstractGetDispatcherState {

    protected GetDispatcher(Builder builder) {
        super(builder);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(CourseUri.REL_PATH, CourseRelTypes.GET_ALL_COURSES);
        addLink(StudentUri.REL_PATH, StudentRelTypes.GET_ALL_STUDENTS);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractDispatcherStateBuilder {
        @Override
        public GetDispatcher build() {
            return new GetDispatcher( this );
        }
    }
}
