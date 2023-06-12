package de.fhws.fiw.fds.suttondemoSql.server.database.utils;

import de.fhws.fiw.fds.suttondemoSql.server.database.DaoFactory;

public class ResetDatabase {
	public static void reset() {
		DaoFactory.getInstance().getPersonDao().resetDatabase();
	}
}
