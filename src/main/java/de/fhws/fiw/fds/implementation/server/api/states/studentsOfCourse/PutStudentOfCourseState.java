package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.EntityTag;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class PutStudentOfCourseState extends AbstractPutRelationState<Student> {
    public PutStudentOfCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Student> loadModel() {
        return DaoFactory.getInstance().getStudentDao().readById(requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getStudentsOfCourseDao().update(primaryId, modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(StudentsOfCourseUri.REL_PATH_ID, StudentsOfCourseRelTypes.GET_STUDENT, primaryId, requestedId);
    }

    @Override
    protected boolean clientDoesNotKnowCurrentModelState(final AbstractModel modelFromDatabase) {
        EntityTag entityTag = EtagGenerator.createEntityTag(modelFromDatabase);
        return this.request.evaluatePreconditions(entityTag) == null;
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractPutRelationStateBuilder<Student> {

        @Override
        public AbstractState build() {
            return new PutStudentOfCourseState(this);
        }
    }
}
