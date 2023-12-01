package de.fhws.fiw.fds.implementation.server.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import jakarta.persistence.Entity;

@Entity
public class StudentCourseDB extends AbstractDBRelation<StudentDB, CourseDB> {
}
