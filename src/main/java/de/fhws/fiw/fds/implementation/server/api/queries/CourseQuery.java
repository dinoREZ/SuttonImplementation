package de.fhws.fiw.fds.implementation.server.api.queries;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class CourseQuery extends AbstractQuery<Course> {
    private String name;

    public CourseQuery(String name, int offset, int size) {
        this.name = name;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Course>(offset, size);
    }

    @Override
    protected CollectionModelResult<Course> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getCourseDao().readByQuery(name, searchParameter);
    }
}
