package de.fhws.fiw.fds.messageSuttondemo.api.states;

import de.fhws.fiw.fds.messageSuttondemo.database.DaoFactory;
import de.fhws.fiw.fds.messageSuttondemo.model.Message;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;


// Step 1 extends AbstractGetState<Message>
// Step 2 implement required Methods (e.g. load model from DB)
// Step 3 Builder Pattern --> AbstractGetStateBuilder
// Step 4 Modify constructor to meet new specifications
public class GetSingleMessage extends AbstractGetState<Message> {

    public GetSingleMessage(Builder  builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Message> loadModel() {
        return DaoFactory.getInstance().getMessageDao().readById(this.requestedId);
    }

    @Override
    protected void defineTransitionLinks() {

    }





    public static class Builder extends AbstractGetStateBuilder{
        @Override
        public AbstractState build() {
            return new GetSingleMessage(this);
        }


    }
}
