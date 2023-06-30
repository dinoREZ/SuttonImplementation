package de.fhws.fiw.fds.suttondemoHibernate.server.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDbRelation;
import jakarta.persistence.Entity;

@Entity
public class PersonLocationDB extends AbstractDbRelation<PersonDB, LocationDB> {

    // marker class with @Entity Annotation
}
