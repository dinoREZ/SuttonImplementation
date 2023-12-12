package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.database.hibernate.CourseDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.CoursesOfStudentDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.StudentDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.StudentsOfCourseDaoAdapter;

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

    private final StudentsOfCourseDao studentsOfCourseDao;

    private DaoFactory() {
        this.studentDao = new StudentDaoAdapter();
        this.courseDao = new CourseDaoAdapter();
        this.coursesOfStudentDao = new CoursesOfStudentDaoAdapter();
        this.studentsOfCourseDao = new StudentsOfCourseDaoAdapter();
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

    public StudentsOfCourseDao getStudentsOfCourseDao() {
        return studentsOfCourseDao;
    }
}
