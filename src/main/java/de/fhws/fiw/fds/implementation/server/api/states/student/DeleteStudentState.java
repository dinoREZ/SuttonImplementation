package de.fhws.fiw.fds.implementation.server.api.states.student;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteStudentState extends AbstractDeleteState<Student> {

    public DeleteStudentState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Student> loadModel() {
        return DaoFactory.getInstance().getStudentDao().readById(modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getStudentDao().delete(modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractDeleteStateBuilder {
        @Override
        public DeleteStudentState build() {
            return new DeleteStudentState( this );
        }
    }
}
