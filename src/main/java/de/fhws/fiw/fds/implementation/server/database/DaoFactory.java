package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.database.hibernate.HumanDaoAdapter;

public class DaoFactory {
    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final HumanDao humanDao;

    private DaoFactory() {
        this.humanDao = new HumanDaoAdapter();
    }

    public HumanDao getHumanDao() {
        return this.humanDao;
    }
}
