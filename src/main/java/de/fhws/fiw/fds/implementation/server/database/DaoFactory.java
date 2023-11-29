package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.database.hibernate.StudentDaoAdapter;

public class DaoFactory {
    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final StudentDao studentDao;

    private DaoFactory() {
        this.studentDao = new StudentDaoAdapter();
    }

    public StudentDao getStudentDao() {
        return this.studentDao;
    }
}
