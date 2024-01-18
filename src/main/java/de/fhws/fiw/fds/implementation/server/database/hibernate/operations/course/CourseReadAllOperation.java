package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.course;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class CourseReadAllOperation extends AbstractReadAllOperation<CourseDB> {

    public CourseReadAllOperation(EntityManagerFactory emf, SearchParameter searchParameter) {
        super(emf, CourseDB.class, searchParameter);
    }

    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        return null;
    }
}
