package de.fhws.fiw.fds.implementation.server.api.queries;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class CoursesOfStudentQuery extends AbstractRelationQuery<Course> {
    private String name;
//

    public CoursesOfStudentQuery(long primaryId, String name, int offset, int size) {
        super(primaryId);
        this.name = name;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Course>(offset, size);
    }

    @Override
    protected CollectionModelResult<Course> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getCoursesOfStudentDao().readByQuery(this.primaryId, this.name, searchParameter);
    }
}
