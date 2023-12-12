package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class DeleteStudentOfCourseState extends AbstractDeleteRelationState<Student> {
    public DeleteStudentOfCourseState(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Student> loadModel() {
        return DaoFactory.getInstance().getStudentsOfCourseDao().readById(primaryId, modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getStudentsOfCourseDao().deleteRelation(primaryId, modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(StudentsOfCourseUri.REL_PATH, StudentsOfCourseRelTypes.GET_ALL_LINKED_STUDENTS, primaryId);
    }

    public static class Builder extends AbstractDeleteRelationStateBuilder {

        @Override
        public AbstractState build() {
            return new DeleteStudentOfCourseState(this);
        }
    }
}
