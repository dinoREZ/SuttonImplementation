package de.fhws.fiw.fds.messageSuttondemo.api.states;

import de.fhws.fiw.fds.messageSuttondemo.database.DaoFactory;
import de.fhws.fiw.fds.messageSuttondemo.model.Message;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;


// Step 1 extend AbstractDeleteState <Message>
// Step 2 implement required methods (e.g. load model from DB and delete loaded model )
// Step 3 Builder Patter --> AbstractDeleteStateBuilder
// Step 4 Modify constructor to meet new specifications
public class DeleteMessage extends AbstractDeleteState<Message> {

    public DeleteMessage(Builder  builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<Message> loadModel() {
        return DaoFactory.getInstance().getMessageDao().readById(this.modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().getMessageDao().delete(this.modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {

    }


    public static class Builder extends AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new DeleteMessage(this);
        }



    }
}
