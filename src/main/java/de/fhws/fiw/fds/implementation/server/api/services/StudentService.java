package de.fhws.fiw.fds.implementation.server.api.services;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.api.queries.CoursesOfStudentQuery;
import de.fhws.fiw.fds.implementation.server.api.queries.StudentQuery;
import de.fhws.fiw.fds.implementation.server.api.rateLimiting.AnyApiKeyRateLimiter;
import de.fhws.fiw.fds.implementation.server.api.states.coursesOfStudent.*;
import de.fhws.fiw.fds.implementation.server.api.states.student.DeleteStudentState;
import de.fhws.fiw.fds.implementation.server.api.states.student.GetStudentCollectionState;
import de.fhws.fiw.fds.implementation.server.api.states.student.GetStudentState;
import de.fhws.fiw.fds.implementation.server.api.states.student.PostStudentState;
import de.fhws.fiw.fds.implementation.server.api.states.student.PutStudentState;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
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
            @DefaultValue("") @QueryParam("lastName")final String lastName,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        StudentQuery query = new StudentQuery(firstName, lastName, offset, size);
        return new GetStudentCollectionState.Builder()
                .setQuery(query)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @GET
    @Path("{studentId: \\d+}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoursesOfStudent(
            @PathParam("studentId") final long studentId,
            @DefaultValue("") @QueryParam("name") final String name,
            @QueryParam("roomNumber") final Integer roomNumber,
            @DefaultValue("") @QueryParam("orderBy") String orderBy,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("20") @QueryParam("size") int size) {
        return new GetCoursesOfStudentState.Builder()
                .setParentId(studentId)
                .setQuery(new CoursesOfStudentQuery(studentId, name, roomNumber).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)).setOrderByAttributes(orderBy))
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
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
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @DELETE
    @Path("{studentId: \\d+}/courses/{courseId: \\d+}")
    public Response deleteCourseOfStudent(@PathParam("studentId") final long studentId,
                                          @PathParam("courseId") final long courseId) {
        return new DeleteCourseOfStudentState.Builder()
                .setParentId(studentId)
                .setRequestedId(courseId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }
}
