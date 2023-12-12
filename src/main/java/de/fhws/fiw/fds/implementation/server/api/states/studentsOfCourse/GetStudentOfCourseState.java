package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

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

    }

    public static class Builder extends AbstractGetRelationStateBuilder {

        @Override
        public AbstractState build() {
            return new GetStudentOfCourseState(this);
        }
    }
}
