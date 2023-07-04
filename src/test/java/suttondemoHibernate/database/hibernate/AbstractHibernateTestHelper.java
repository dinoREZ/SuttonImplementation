package suttondemoHibernate.database.hibernate;

import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.dao.LocationDaoHibernateImpl;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.dao.PersonDaoHibernateImpl;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.utils.ResetDatabase;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractHibernateTestHelper {

    @BeforeEach
    public void resetDatabase() {
        ResetDatabase.resetAll();

        assertTrue(new PersonDaoHibernateImpl().readAll().isEmpty());
        assertTrue(new LocationDaoHibernateImpl().readAll().isEmpty());
    }

}
