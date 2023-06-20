package de.fhws.fiw.fds.suttondemoHibernate.server.database.utils;

import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;

public class ResetDatabase {

    public static void reset() {
        DaoFactory.getInstance().getPersonDao().resetDatabase();
    }

}
