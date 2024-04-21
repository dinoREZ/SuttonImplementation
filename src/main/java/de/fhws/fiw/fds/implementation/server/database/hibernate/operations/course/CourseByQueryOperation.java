package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class CourseByQueryOperation extends AbstractReadAllOperation<CourseDB> {

    private final String name;
    private final Integer roomNumber;

    public CourseByQueryOperation(EntityManagerFactory emf,String name, Integer roomNumber, SearchParameter searchParameter) {
        super(emf, CourseDB.class, searchParameter);
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

