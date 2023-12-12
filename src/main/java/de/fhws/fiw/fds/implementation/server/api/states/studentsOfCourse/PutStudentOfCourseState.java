package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

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

    public static class Builder extends AbstractPutRelationStateBuilder<Student> {

        @Override
        public AbstractState build() {
            return new PutStudentOfCourseState(this);
        }
    }
}
