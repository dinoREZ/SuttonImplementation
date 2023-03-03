package de.fhws.fiw.fds.messageSuttondemo.api.states;

import de.fhws.fiw.fds.messageSuttondemo.database.DaoFactory;
import de.fhws.fiw.fds.messageSuttondemo.model.Message;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

//Step 1 extends AbstractGetCollectionState <Message>
//Step 2 Implement required Methods (e.g. define the ResponseBody )
//Step 3 extends AbstractQuery as an inner Class to define the query statement to fetch the data
//Step 4 Builder Pattern (Builder class) --> AbstractGetCollectionStateBuilder<Message>
//Step 5 Modify constructor to meet new specifications
public class GetAllMessages extends AbstractGetCollectionState<Message>{


    protected GetAllMessages(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected void defineHttpResponseBody() {
    }

    @Override
    protected void defineTransitionLinks() {

    }


    public static class AllMessages extends AbstractQuery<Message> {
        @Override
        protected CollectionModelResult<Message> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            return DaoFactory.getInstance().getMessageDao().readAll();
                  // Auf Unit 7 ist anstatt  readByPredicate(all()); --> readAll
        }


    }

    public static class Builder extends AbstractGetCollectionStateBuilder {
        @Override
        public AbstractState build() {
            return new GetAllMessages(this);
        }



    }
}
