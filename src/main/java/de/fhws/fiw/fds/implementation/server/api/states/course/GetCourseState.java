package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

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

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetCourseState( this );
        }
    }
}
