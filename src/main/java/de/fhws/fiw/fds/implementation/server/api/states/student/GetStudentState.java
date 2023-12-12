package de.fhws.fiw.fds.implementation.server.api.states.student;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

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

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new GetStudentState( this );
        }
    }
}
