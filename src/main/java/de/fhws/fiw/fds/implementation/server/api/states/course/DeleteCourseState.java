package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class DeleteCourseState extends AbstractDeleteState<Course> {
    public DeleteCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Course> loadModel() {
        return DaoFactory.getInstance().getCourseDao().readById(modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        DaoFactory.getInstance().getStudentsOfCourseDao().deleteRelationsFromPrimary(modelIdToDelete);
        return DaoFactory.getInstance().getCourseDao().delete(modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(CourseUri.REL_PATH, CourseRelTypes.GET_ALL_COURSES);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new DeleteCourseState(this);
        }
    }
}
