package de.fhws.fiw.fds.suttondemoHibernate.server.database.utils;

import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;

public class ResetDatabase {

    public static void resetAll() {
        DaoFactory.getInstance().getPersonLocationDao().resetDatabase();
    }

}
