package de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteCourseOfStudentState extends AbstractDeleteRelationState<Course> {
    public DeleteCourseOfStudentState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Course> loadModel() {
        return DaoFactory.getInstance().getCoursesOfStudentDao().readById(primaryId, modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getCoursesOfStudentDao().deleteRelation(primaryId, modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    public static class Builder extends AbstractDeleteRelationStateBuilder {

        @Override
        public AbstractState build() {
            return new DeleteCourseOfStudentState(this);
        }
    }
}
