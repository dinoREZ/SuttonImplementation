package de.fhws.fiw.fds.messageSuttondemo.database;

import de.fhws.fiw.fds.messageSuttondemo.model.Message;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;

public class MessageInMemoryStorage extends AbstractInMemoryStorage<Message> implements  MessageDao{
    public MessageInMemoryStorage() {
        super();
        populateData();
    }

    private void populateData() {
        for (int i = 0; i < 5; i++) {
            create(new Message("Text" + i , "Author"+ i ));
        }

    }
}
