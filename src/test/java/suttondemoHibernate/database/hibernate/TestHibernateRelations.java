package suttondemoHibernate.database.hibernate;

import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.dao.*;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models.PersonDB;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestHibernateRelations extends AbstractHibernateTestHelper {

    @Test
    public void test_db_save_successful() throws Exception {
        //Person
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate personDao = new PersonDaoHibernateImpl();
        NoContentResult resultSavePerson = personDao.create(person);

        assertFalse(resultSavePerson.hasError());

        CollectionModelHibernateResult<PersonDB> personResultGetAll = personDao.readAll();
        assertEquals(1, personResultGetAll.getResult().size());

        // Relation to Location
        LocationDB location = new LocationDB();
        location.setCityName("London");
        location.setVisitedOn(LocalDate.of(2021, 9, 30));
        location.setLongitude(-0.118092);
        location.setLatitude(51.509865);

        PersonLocationDaoHibernate relDao = new PersonLocationDaoHibernateImpl();
        NoContentResult resultSaveRel = relDao.create(person.getId(), location);
        assertFalse(resultSaveRel.hasError());

        CollectionModelHibernateResult<LocationDB> locationResultGetAllById = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(1, locationResultGetAllById.getResult().size());
    }

    @Test
    public void test_db_delete_by_id() throws Exception {
        //Person
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate personDao = new PersonDaoHibernateImpl();
        NoContentResult resultSavePerson = personDao.create(person);
        assertFalse(resultSavePerson.hasError());

        // Relation to Location
        LocationDB location = new LocationDB();
        location.setCityName("London");
        location.setVisitedOn(LocalDate.of(2021, 9, 30));
        location.setLongitude(-0.118092);
        location.setLatitude(51.509865);

        PersonLocationDaoHibernate relDao = new PersonLocationDaoHibernateImpl();
        NoContentResult resultSaveRel = relDao.create(person.getId(), location);
        assertFalse(resultSaveRel.hasError());

        CollectionModelHibernateResult<LocationDB> locationResultGetAllByIdBeforeDeletion = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(1, locationResultGetAllByIdBeforeDeletion.getResult().size());

        NoContentResult resultDelete = relDao.deleteRelation(person.getId(), location.getId());
        assertFalse(resultDelete.hasError());

        // Only Relation should have been deleted
        CollectionModelHibernateResult<PersonDB> personResultGetAll = personDao.readAll();
        assertEquals(1, personResultGetAll.getResult().size());
        CollectionModelHibernateResult<LocationDB> locationResultGetAllById = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(0, locationResultGetAllById.getResult().size());
        CollectionModelHibernateResult<LocationDB> locationResultGetAll = new LocationDaoHibernateImpl().readAll();
        assertEquals(1, locationResultGetAll.getResult().size());
    }

    @Test
    public void test_db_delete_by_primary_id() throws Exception {
        //Person
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate personDao = new PersonDaoHibernateImpl();
        NoContentResult resultSavePerson = personDao.create(person);
        assertFalse(resultSavePerson.hasError());

        // Relation to Location
        LocationDB location = new LocationDB();
        location.setCityName("London");
        location.setVisitedOn(LocalDate.of(2021, 9, 30));
        location.setLongitude(-0.118092);
        location.setLatitude(51.509865);

        PersonLocationDaoHibernate relDao = new PersonLocationDaoHibernateImpl();
        NoContentResult resultSaveRel = relDao.create(person.getId(), location);
        assertFalse(resultSaveRel.hasError());

        CollectionModelHibernateResult<LocationDB> locationResultGetAllByIdBeforeDeletion = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(1, locationResultGetAllByIdBeforeDeletion.getResult().size());

        NoContentResult resultDelete = relDao.deleteRelationsFromPrimary(person.getId());
        assertFalse(resultDelete.hasError());

        // Only Relation should have been deleted
        CollectionModelHibernateResult<PersonDB> personResultGetAll = personDao.readAll();
        assertEquals(1, personResultGetAll.getResult().size());
        CollectionModelHibernateResult<LocationDB> locationResultGetAllById = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(0, locationResultGetAllById.getResult().size());
        CollectionModelHibernateResult<LocationDB> locationResultGetAll = new LocationDaoHibernateImpl().readAll();
        assertEquals(1, locationResultGetAll.getResult().size());
    }

    @Test
    public void test_db_delete_by_secondary_id() throws Exception {
        //Person
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate personDao = new PersonDaoHibernateImpl();
        NoContentResult resultSavePerson = personDao.create(person);
        assertFalse(resultSavePerson.hasError());

        // Relation to Location
        LocationDB location = new LocationDB();
        location.setCityName("London");
        location.setVisitedOn(LocalDate.of(2021, 9, 30));
        location.setLongitude(-0.118092);
        location.setLatitude(51.509865);

        PersonLocationDaoHibernate relDao = new PersonLocationDaoHibernateImpl();
        NoContentResult resultSaveRel = relDao.create(person.getId(), location);
        assertFalse(resultSaveRel.hasError());

        CollectionModelHibernateResult<LocationDB> locationResultGetAllByIdBeforeDeletion = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(1, locationResultGetAllByIdBeforeDeletion.getResult().size());

        NoContentResult resultDelete = relDao.deleteRelationsToSecondary(location.getId());
        assertFalse(resultDelete.hasError());

        // Only Relation should have been deleted
        CollectionModelHibernateResult<PersonDB> personResultGetAll = personDao.readAll();
        assertEquals(1, personResultGetAll.getResult().size());
        CollectionModelHibernateResult<LocationDB> locationResultGetAllById = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(0, locationResultGetAllById.getResult().size());
        CollectionModelHibernateResult<LocationDB> locationResultGetAll = new LocationDaoHibernateImpl().readAll();
        assertEquals(1, locationResultGetAll.getResult().size());
    }

    @Test
    public void test_db_update() throws Exception {
        //Person
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate personDao = new PersonDaoHibernateImpl();
        NoContentResult resultSavePerson = personDao.create(person);
        assertFalse(resultSavePerson.hasError());

        // Relation to Location
        LocationDB location = new LocationDB();
        location.setCityName("London");
        location.setVisitedOn(LocalDate.of(2021, 9, 30));
        location.setLongitude(-0.118092);
        location.setLatitude(51.509865);

        PersonLocationDaoHibernate relDao = new PersonLocationDaoHibernateImpl();
        NoContentResult resultSaveRel = relDao.create(person.getId(), location);
        assertFalse(resultSaveRel.hasError());

        CollectionModelHibernateResult<LocationDB> locationResultGetAllByIdBeforeDeletion = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(1, locationResultGetAllByIdBeforeDeletion.getResult().size());

        SingleModelHibernateResult<LocationDB> resultGetById = relDao.readById(person.getId(), location.getId());
        LocationDB locationInDB = resultGetById.getResult();
        locationInDB.setCityName("Berlin");

        NoContentResult resultUpdate = relDao.update(person.getId(), locationInDB);
        assertFalse(resultUpdate.hasError());

        SingleModelHibernateResult<LocationDB> resultGetByIdAfterUpdate = relDao.readById(person.getId(), location.getId());
        LocationDB locationInDBAfterUpdate = resultGetById.getResult();
        assertEquals("Berlin", locationInDBAfterUpdate.getCityName());
    }

    @Test
    public void test_db_update_with_new_relation() throws Exception {
        //Person
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate personDao = new PersonDaoHibernateImpl();
        NoContentResult resultSavePerson = personDao.create(person);
        assertFalse(resultSavePerson.hasError());

        // Relation to Location
        LocationDB location = new LocationDB();
        location.setCityName("London");
        location.setVisitedOn(LocalDate.of(2021, 9, 30));
        location.setLongitude(-0.118092);
        location.setLatitude(51.509865);

        LocationDaoHibernate locationDao = new LocationDaoHibernateImpl();
        NoContentResult resultSaveLocation = locationDao.create(location);
        assertFalse(resultSaveLocation.hasError());

        // there should be no relation
        PersonLocationDaoHibernate relDao = new PersonLocationDaoHibernateImpl();
        CollectionModelHibernateResult<LocationDB> locationResultGetAllById = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(0, locationResultGetAllById.getResult().size());

        // update location to person relation
        SingleModelHibernateResult<LocationDB> resultGetById = locationDao.readById(location.getId());
        LocationDB locationInDB = resultGetById.getResult();
        locationInDB.setCityName("Berlin");

        NoContentResult resultUpdate = relDao.update(person.getId(), locationInDB);
        assertFalse(resultUpdate.hasError());
        CollectionModelHibernateResult<LocationDB> locationResultGetAllByIdAfterUpdate = relDao.readAllByPredicate(person.getId(), locationDB -> true);
        assertEquals(1, locationResultGetAllByIdAfterUpdate.getResult().size());
    }
}
