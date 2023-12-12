package de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse;

public interface StudentsOfCourseRelTypes {
    String CREATE_STUDENT = "createStudentOfCourse";
    String GET_ALL_LINKED_STUDENTS = "getAllStudentsOfCourse";
    String GET_ALL_STUDENTS = "getAllLinkableStudents";
    String UPDATE_STUDENT = "updateStudentOfCourse";
    String CREATE_LINK_FROM_COURSE_TO_STUDENT = "linkCourseToStudent";
    String DELETE_LINK_FROM_COURSE_TO_STUDENT = "unlinkCourseToStudent";
    String GET_STUDENT = "getStudentOfCourse";
}
