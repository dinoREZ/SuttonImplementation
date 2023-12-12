package de.fhws.fiw.fds.implementation.server.api.states.student;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;

public class GetStudentState extends AbstractGetState<Student> {


    public GetStudentState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Student> loadModel() {
        return DaoFactory.getInstance().getStudentDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(StudentUri.REL_PATH_ID, StudentRelTypes.UPDATE_STUDENT, requestedId);
        addLink(StudentUri.REL_PATH_ID, StudentRelTypes.DELETE_STUDENT, requestedId);
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

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetStudentState( this );
        }
    }
}
