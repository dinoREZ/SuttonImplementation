package de.fhws.fiw.fds.implementation.server.api.services;

import de.fhws.fiw.fds.implementation.server.api.models.Course;
import de.fhws.fiw.fds.implementation.server.api.models.Student;
import de.fhws.fiw.fds.implementation.server.api.queries.CourseQuery;
import de.fhws.fiw.fds.implementation.server.api.queries.StudentsOfCourseQuery;
import de.fhws.fiw.fds.implementation.server.api.rateLimiting.AnyApiKeyRateLimiter;
import de.fhws.fiw.fds.implementation.server.api.states.course.*;
import de.fhws.fiw.fds.implementation.server.api.states.studentsOfCourse.*;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("courses")
public class CourseService extends AbstractService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses(@DefaultValue("") @QueryParam("name") final String name,
                                  @QueryParam("roomNumber") final Integer roomNumber,
                                  @DefaultValue("") @QueryParam("orderBy") String orderBy,
                                  @DefaultValue("0") @QueryParam("offset") int offset,
                                  @DefaultValue("20") @QueryParam("size") int size) {
        return new GetCourseCollectionState.Builder()
                .setQuery(new CourseQuery(name, roomNumber).setPagingBehavior(new PagingBehaviorUsingOffsetSize(offset, size)).setOrderByAttributes(orderBy))
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
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourse(@PathParam("id") final long id) {
        return new GetCourseState.Builder()
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
    public Response createCourse(final Course course) {
        return new PostCourseState.Builder()
                .setModelToCreate(course)
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
    public Response updateCourse(@PathParam("id") long id, final Course course) {
        return new PutCourseState.Builder()
                .setModelToUpdate(course)
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
    public Response deleteCourse(@PathParam("id") final long id) {
        return new DeleteCourseState.Builder()
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
    @Path("{courseId : \\d+}/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsOfCourse(@PathParam("courseId") final long courseId,
                                        @DefaultValue("") @QueryParam("firstName") final String firstName,
                                        @DefaultValue("0") @QueryParam("offset") int offset,
                                        @DefaultValue("20") @QueryParam("size") int size) {
        return new GetStudentsOfCourseState.Builder()
                .setParentId(courseId)
                .setQuery(new StudentsOfCourseQuery(courseId, firstName, offset, size))
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
    @Path("{courseId: \\d+}/students/{studentId: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentOfCourseById(@PathParam("courseId") final long courseId,
                                           @PathParam("studentId") final long studentId) {
        return new GetStudentOfCourseState.Builder()
                .setParentId(courseId)
                .setRequestedId(studentId)
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
    @Path("{courseId: \\d+}/students")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudentOfCourse(@PathParam("courseId") final long courseId, final Student student) {
        return new PostStudentOfCourseState.Builder()
                .setParentId(courseId)
                .setModelToCreate(student)
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
    @Path("{courseId: \\d+}/students/{studentId: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudentOfCourse(@PathParam("courseId") final long courseId,
                                          @PathParam("studentId") final long studentId, final Student student) {
        return new PutStudentOfCourseState.Builder()
                .setParentId(courseId)
                .setRequestedId(studentId)
                .setModelToUpdate(student)
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
    @Path("{courseId: \\d+}/students/{studentId: \\d+}")
    public Response deleteStudentOfCourse(@PathParam("courseId") final long courseId,
                                          @PathParam("studentId") final long studentId) {
        return new DeleteStudentOfCourseState.Builder()
                .setParentId(courseId)
                .setRequestedId(studentId)
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
