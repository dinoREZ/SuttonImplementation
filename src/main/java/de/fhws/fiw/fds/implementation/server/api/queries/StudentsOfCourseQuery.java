package de.fhws.fiw.fds.implementation.server.api.queries;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class StudentsOfCourseQuery extends AbstractRelationQuery<Student> {

    private String firstName;

    public StudentsOfCourseQuery(long primaryId, String firstName, int offset, int size) {
        super(primaryId);
        this.firstName = firstName;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Student>(offset, size);
    }

    @Override
    protected CollectionModelResult<Student> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getStudentsOfCourseDao().readByQuery(primaryId, firstName, searchParameter);
    }
}
