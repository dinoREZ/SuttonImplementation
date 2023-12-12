package de.fhws.fiw.fds.implementation.server.api.states.course;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.EntityTag;

public class PutCourseState extends AbstractPutState<Course> {
    protected PutCourseState(Builder builder) {
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
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getCourseDao().update(modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(CourseUri.REL_PATH_ID, CourseRelTypes.GET_COURSE, requestedId);
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(final AbstractModel modelFromDatabase) {
        EntityTag entityTag = EtagGenerator.createEntityTag(modelFromDatabase);
        return this.request.evaluatePreconditions(entityTag) == null;
    }

    public static class Builder extends AbstractPutStateBuilder<Course> {
        @Override
        public AbstractState build() {
            return new PutCourseState(this);
        }
    }
}
