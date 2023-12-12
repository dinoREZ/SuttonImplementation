package de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent;

public interface CoursesOfStudentRelTypes {
    String CREATE_COURSE = "createCourseOfStudent";
    String GET_ALL_LINKED_COURSES = "getAllCoursesOfStudent";
    String GET_ALL_COURSES = "getAllLinkableCourses";
    String UPDATE_COURSE = "updateCourseOfStudent";
    String CREATE_LINK_FROM_STUDENT_TO_COURSE = "linkStudentToCourse";
    String DELETE_LINK_FROM_STUDENT_TO_COURSE = "unlinkStudentToCourse";
    String GET_COURSE = "getCourseOfStudent";
}
