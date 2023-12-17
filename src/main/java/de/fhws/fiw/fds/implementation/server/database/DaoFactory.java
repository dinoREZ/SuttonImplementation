package de.fhws.fiw.fds.implementation.server.database;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.database.hibernate.CourseDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.CoursesOfStudentDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.StudentDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.hibernate.StudentsOfCourseDaoAdapter;
import de.fhws.fiw.fds.implementation.server.database.inmemory.CourseDaoImpl;
import de.fhws.fiw.fds.implementation.server.database.inmemory.CoursesOfStudentDaoImpl;
import de.fhws.fiw.fds.implementation.server.database.inmemory.StudentDaoImpl;
import de.fhws.fiw.fds.implementation.server.database.inmemory.StudentsOfCourseDaoImpl;

public class DaoFactory {

    static boolean useInMemory = false;
    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DaoFactory();
            INSTANCE.insertData();
        }

        return INSTANCE;
    }

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    private CoursesOfStudentDao coursesOfStudentDao;

    private final StudentsOfCourseDao studentsOfCourseDao;

    private DaoFactory() {
        if(useInMemory) {
            this.studentDao = new StudentDaoImpl();
            this.courseDao = new CourseDaoImpl();
            StudentsOfCourseDaoImpl studentsOfCourseDaoImpl = new StudentsOfCourseDaoImpl();
            this.studentsOfCourseDao = studentsOfCourseDaoImpl;
            this.coursesOfStudentDao = new CoursesOfStudentDaoImpl(studentsOfCourseDaoImpl);
        } else {
            this.studentDao = new StudentDaoAdapter();
            this.courseDao = new CourseDaoAdapter();
            this.studentsOfCourseDao = new StudentsOfCourseDaoAdapter();
            this.coursesOfStudentDao = new CoursesOfStudentDaoAdapter();
        }
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

    private void insertData() {
        Student student = new Student();
        student.setFirstName("Max");
        student.setLastName("Mustermann");
        getStudentDao().create(student);

        Course course = new Course();
        course.setName("Test Course");
        getCoursesOfStudentDao().create(student.getId(), course);
    }
}
