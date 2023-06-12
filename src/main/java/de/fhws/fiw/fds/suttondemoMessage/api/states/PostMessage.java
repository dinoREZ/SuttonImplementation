package de.fhws.fiw.fds.suttondemoMessage.api.states;

import de.fhws.fiw.fds.suttondemoMessage.database.DaoFactory;
import de.fhws.fiw.fds.suttondemoMessage.model.Message;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

// Step 1 extend AbstractPostState<Message>
// Step 2 implement required methods (e.g. save model )
// Step 3 Builder Pattern --> AbstractPostStateBuilder<Message>
// Step 4 Modify constructor to meet new specifications
public class PostMessage extends AbstractPostState<Message> {

    protected PostMessage(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getMessageDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }


    public static class Builder extends AbstractPostStateBuilder {
        @Override
        public AbstractState build() {
            return new PostMessage(this);
        }



    }
}
