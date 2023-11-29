package de.fhws.fiw.fds.implementation.server.api.queries;

import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class StudentQuery extends AbstractQuery<Student> {

    private String firstName;
    private String lastName;

    public StudentQuery(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected CollectionModelResult<Student> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getStudentDao().readByQuery(firstName, lastName);
    }
}
