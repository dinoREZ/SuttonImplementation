package de.fhws.fiw.fds.implementation.server.api.states.student;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class PutStudentState extends AbstractPutState<Student> {
    protected PutStudentState(Builder builder) {
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
        return DaoFactory.getInstance().getStudentDao().update(modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(StudentUri.REL_PATH_ID, StudentRelTypes.GET_STUDENT, requestedId);
    }

    public static class Builder extends AbstractPutStateBuilder<Student> {
        @Override
        public PutStudentState build() {
            return new PutStudentState(this);
        }
    }
}
