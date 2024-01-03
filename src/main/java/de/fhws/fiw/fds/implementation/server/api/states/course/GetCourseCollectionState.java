package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

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

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<Course> {
        @Override
        public AbstractState build() {
            return new GetCourseCollectionState(this);
        }
    }
}
