package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;

public class GetStudentOfCourseState extends AbstractGetRelationState<Student> {
    public GetStudentOfCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Student> loadModel() {
        return DaoFactory.getInstance().getStudentsOfCourseDao().readById(primaryId, requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(StudentsOfCourseUri.REL_PATH, StudentsOfCourseRelTypes.GET_ALL_LINKED_STUDENTS, primaryId);
        addLink(StudentsOfCourseUri.REL_PATH_ID, StudentsOfCourseRelTypes.UPDATE_STUDENT, primaryId, requestedId);
        addLink(StudentsOfCourseUri.REL_PATH_ID, StudentsOfCourseRelTypes.DELETE_LINK_FROM_COURSE_TO_STUDENT, primaryId, requestedId);
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

    public static class Builder extends AbstractGetRelationStateBuilder {

        @Override
        public AbstractState build() {
            return new GetStudentOfCourseState(this);
        }
    }
}
