package de.fhws.fiw.fds.messageSuttondemo.api.states;

import de.fhws.fiw.fds.messageSuttondemo.database.DaoFactory;
import de.fhws.fiw.fds.messageSuttondemo.model.Message;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;


// Step 1 extends AbstractPutState <Message>
// Step 2 implement required methods (e.g. loadModel , update model )
// Step 3 Builder Pattern --> AbstractPutStateBuilder <Message>
// Step 4 Modify constructor to meet new specifications
public class PutMessage extends AbstractPutState<Message> {

    protected PutMessage(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Message> loadModel() {
        return DaoFactory.getInstance().getMessageDao().readById(this.modelToUpdate.getId());
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().getMessageDao().update(this.modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {

    }


    public static class Builder extends AbstractPutStateBuilder{
        @Override
        public AbstractState build() {
            return new PutMessage(this);
        }



    }
}
