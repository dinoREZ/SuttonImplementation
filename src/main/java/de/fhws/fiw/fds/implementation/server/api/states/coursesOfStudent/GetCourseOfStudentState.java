package de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

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
        addLink(CoursesOfStudentUri.REL_PATH, CoursesOfStudentRelTypes.GET_ALL_LINKED_COURSES, primaryId);
        addLink(CoursesOfStudentUri.REL_PATH_ID, CoursesOfStudentRelTypes.UPDATE_COURSE, primaryId, requestedId);
        addLink(CoursesOfStudentUri.REL_PATH_ID, CoursesOfStudentRelTypes.DELETE_LINK_FROM_STUDENT_TO_COURSE, primaryId, requestedId);
    }

    @Override
    protected boolean clientKnowsCurrentModelState( final AbstractModel modelFromDatabase ) {
        EntityTag entityTag = EtagGenerator.createEntityTag(modelFromDatabase);
        return this.request.evaluatePreconditions(entityTag) != null;
    }

    @Override
    protected void defineHttpCaching() {
        CacheControl cacheControl = CachingUtils.create60SecondsPublicCaching();
        this.responseBuilder.cacheControl(cacheControl);
        this.responseBuilder.tag(EtagGenerator.createEntityTag(this.requestedModel.getResult()));
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetRelationStateBuilder {
        @Override
        public AbstractState build() {
            return new GetCourseOfStudentState(this);
        }
    }
}
