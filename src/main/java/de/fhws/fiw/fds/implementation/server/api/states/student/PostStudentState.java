package de.fhws.fiw.fds.implementation.server.api.states.student;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostStudentState extends AbstractPostState<Student> {
    protected PostStudentState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getStudentDao().create(modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractPostStateBuilder<Student> {
        @Override
        public PostStudentState build() {
            return new PostStudentState(this);
        }
    }
}
