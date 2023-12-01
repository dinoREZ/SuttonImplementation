package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.database.hibernate.CourseDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.CoursesOfStudentDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.StudentDaoAdapter;

public class DaoFactory {
    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    private final CoursesOfStudentDao coursesOfStudentDao;

    private DaoFactory() {
        this.studentDao = new StudentDaoAdapter();
        this.courseDao = new CourseDaoAdapter();
        this.coursesOfStudentDao = new CoursesOfStudentDaoAdapter();
    }

    public StudentDao getStudentDao() {
        return this.studentDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public CoursesOfStudentDao getCoursesOfStudentDao() {
        return coursesOfStudentDao;
    }
}
