package de.fhws.fiw.fds.implementation.server.database.hibernate.operations.coursesOfStudent;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.CourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentCourseDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
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

public class CoursesOfStudentByQueryOperation extends AbstractDatabaseOperation<CourseDB, CollectionModelHibernateResult<CourseDB>> {
    private final Class<StudentCourseDB> classOfRelation = StudentCourseDB.class;
    private final long primaryId;
    private final String name;
    private final SearchParameter searchParameter;

    public CoursesOfStudentByQueryOperation(EntityManagerFactory emf, long primaryId, String name, SearchParameter searchParameter) {
        super(emf);
        this.primaryId = primaryId;
        this.name = name;
        this.searchParameter = searchParameter;
    }

    @Override
    protected CollectionModelHibernateResult<CourseDB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CollectionModelHibernateResult<CourseDB> returnValue = new CollectionModelHibernateResult<>(readResults());
        returnValue.setTotalNumberOfResult(getTotalNumberOfResults());
        return returnValue;
    }

    @Override
    protected CollectionModelHibernateResult<CourseDB> errorResult() {
        final CollectionModelHibernateResult<CourseDB> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }

    private List<CourseDB> readResults() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<StudentCourseDB> find = criteriaBuilder.createQuery(StudentCourseDB.class);
        Root<StudentCourseDB> root = find.from(StudentCourseDB.class);
        Join<StudentCourseDB, CourseDB> join = root.join(SuttonColumnConstants.SECONDARY_MODEL);

        find.where(formulateConditions(criteriaBuilder, root, join));

        return this.em
                .createQuery(find)
                .setMaxResults(searchParameter.getSize())
                .setFirstResult(searchParameter.getOffset())
                .getResultList()
                .stream()
                .map(AbstractDBRelation::getSecondaryModel)
                .collect(Collectors.toList());
    }

    private int getTotalNumberOfResults() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> find = criteriaBuilder.createQuery(Long.class);
        Root<StudentCourseDB> root = find.from(StudentCourseDB.class);
        Join<StudentCourseDB, CourseDB> join = root.join(SuttonColumnConstants.SECONDARY_MODEL);

        find.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root, join));

        return this.em
                .createQuery(find)
                .getSingleResult().intValue();
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<StudentCourseDB> root, Join<StudentCourseDB, CourseDB> join) {
        Predicate matchName = criteriaBuilder.like(join.get("name"), "%" + name + "%");
        Predicate primaryIdEquals = criteriaBuilder.equal(root.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.primaryId);

        return criteriaBuilder.and(primaryIdEquals, matchName);
    }
}
