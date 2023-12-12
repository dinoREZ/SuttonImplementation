package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

public class PostStudentOfCourseState extends AbstractPostRelationState<Student> {
    public PostStudentOfCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getStudentsOfCourseDao().create(primaryId, modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractPostRelationStateBuilder<Student> {

        @Override
        public AbstractState build() {
            return new PostStudentOfCourseState(this);
        }
    }
}
