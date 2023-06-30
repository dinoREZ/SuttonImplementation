package de.fhws.fiw.fds.suttondemoHibernate.server.database.utils;

import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;

public class InitializeDatabase {

    public static void initializePersonDB() {
        DaoFactory.getInstance().getPersonDao().initializeDatabase();
    }

    public static void initializeLocationDB() {
        DaoFactory.getInstance().getLocationDao().initializeDatabase();
    }

    public static void initializeDBWithRelations() {
        DaoFactory.getInstance().getPersonLocationDao().initializeDatabase();
    }
}
