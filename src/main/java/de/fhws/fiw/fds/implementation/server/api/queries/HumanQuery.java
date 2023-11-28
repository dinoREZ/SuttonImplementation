package de.fhws.fiw.fds.implementation.server.api.queries;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class HumanQuery extends AbstractQuery<Human> {

    private String firstName;
    private String lastName;

    public HumanQuery(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    protected CollectionModelResult<Human> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getHumanDao().readByQuery(firstName, lastName);
    }
}
