package de.fhws.fiw.fds.suttondemoMessage.database;

public class DaoFactory {
    private static DaoFactory INSTANCE;
    private final MessageDao messageDao ;

    public static final DaoFactory getInstance(){
        if (INSTANCE == null ) INSTANCE = new DaoFactory();
        return INSTANCE ;
    }

    private DaoFactory(){
        this.messageDao  = new MessageInMemoryStorage();
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }
}
