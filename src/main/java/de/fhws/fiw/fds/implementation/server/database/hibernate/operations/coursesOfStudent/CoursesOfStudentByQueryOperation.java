package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractReadAllRelationsByPrimaryIdOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class CoursesOfStudentByQueryOperation extends AbstractReadAllRelationsByPrimaryIdOperation<StudentDB, CourseDB, StudentCourseDB> {
    private final String name;
    private final Integer roomNumber;

    public CoursesOfStudentByQueryOperation(EntityManagerFactory emf, long primaryId, String name, Integer roomNumber, SearchParameter searchParameter) {
        super(emf, StudentCourseDB.class, primaryId, searchParameter);
        this.name = name;
        this.roomNumber = roomNumber;
    }

    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        final Predicate matchName =  cb.like(from.get("name"), "%" + this.name + "%");

        final Predicate matchRoomNumber =  cb.equal(from.get("roomNumber"), this.roomNumber);

        if(roomNumber == null) {
            return List.of(matchName);
        }
        else {
            return List.of(matchName, matchRoomNumber);
        }
    }
}
