package de.fhws.fiw.fds.implementation.server.api.services;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.api.queries.CourseQuery;
import de.fhws.fiw.fds.implementation.server.api.queries.CoursesOfStudentQuery;
import de.fhws.fiw.fds.implementation.server.api.queries.StudentQuery;
import de.fhws.fiw.fds.implementation.server.api.rateLimiting.AnyApiKeyRateLimiter;
import de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent.*;
import de.fhws.fiw.fds.implementation.server.api.states.student.DeleteStudentState;
import de.fhws.fiw.fds.implementation.server.api.states.student.GetStudentCollectionState;
import de.fhws.fiw.fds.implementation.server.api.states.student.GetStudentState;
import de.fhws.fiw.fds.implementation.server.api.states.student.PostStudentState;
import de.fhws.fiw.fds.implementation.server.api.states.student.PutStudentState;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("students")
public class StudentService extends AbstractService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllStudents(
            @DefaultValue("") @QueryParam("firstName") final String firstName,
            @DefaultValue("") @QueryParam("lastName")final String lastName) {
        StudentQuery query = new StudentQuery(firstName, lastName);
        return new GetStudentCollectionState.Builder()
                .setQuery(query)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @GET
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") final long id) {
        return new GetStudentState.Builder()
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(final Student student) {
        return new PostStudentState.Builder()
                .setModelToCreate(student)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @PUT
    @Path("{id : \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") long id, final Student student) {
        return new PutStudentState.Builder()
                .setModelToUpdate(student)
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @DELETE
    @Path("{id : \\d+}")
    public Response deleteStudent(@PathParam("id") final long id) {
        return new DeleteStudentState.Builder()
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @GET
    @Path("{studentId: \\d+}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoursesOfStudent(
            @PathParam("studentId") final long studentId,
            @DefaultValue("") @QueryParam("name") final String name) {
        return new GetCoursesOfStudentState.Builder()
                .setParentId(studentId)
                .setQuery(new CoursesOfStudentQuery(studentId, name))
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @GET
    @Path("{studentId: \\d+}/courses/{courseId: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourseOfStudentById(@PathParam("studentId") final long studentId,
                                           @PathParam("courseId") final long courseId) {
        return new GetCourseOfStudentState.Builder()
                .setParentId(studentId)
                .setRequestedId(courseId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @POST
    @Path("{studentId: \\d+}/courses")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCourseOfStudent(@PathParam("studentId") final long studentId, final Course course) {
        return new PostCourseOfStudentState.Builder()
                .setParentId(studentId)
                .setModelToCreate(course)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @PUT
    @Path("{studentId: \\d+}/courses/{courseId: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCourseOfStudent(@PathParam("studentId") final long studentId,
                                          @PathParam("courseId") final long courseId, final Course course) {
        return new PutCourseOfStudentState.Builder()
                .setParentId(studentId)
                .setRequestedId(courseId)
                .setModelToUpdate(course)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }

    @DELETE
    @Path("{studentId: \\d+}/courses/{courseId: \\d+}")
    public Response deleteLocationOfPerson(@PathParam("studentId") final long studentId,
                                           @PathParam("courseId") final long courseId) {
        return new DeleteCourseOfStudentState.Builder()
                .setParentId(studentId)
                .setRequestedId(courseId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }
}
