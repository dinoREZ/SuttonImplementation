package de.fhws.fiw.fds.implementation.server.api.services;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.api.queries.HumanQuery;
import de.fhws.fiw.fds.implementation.server.api.rateLimiting.AnyApiKeyRateLimiter;
import de.fhws.fiw.fds.implementation.server.api.states.human.DeleteHumanState;
import de.fhws.fiw.fds.implementation.server.api.states.human.GetHumanCollectionState;
import de.fhws.fiw.fds.implementation.server.api.states.human.GetHumanState;
import de.fhws.fiw.fds.implementation.server.api.states.human.PostHumanState;
import de.fhws.fiw.fds.implementation.server.api.states.human.PutHumanState;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("humans")
public class HumanService extends AbstractService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllHumans(
            @DefaultValue("") @QueryParam("firstName") final String firstName,
            @DefaultValue("") @QueryParam("lastName")final String lastName) {
        HumanQuery query = new HumanQuery(firstName, lastName);
        return new GetHumanCollectionState.Builder()
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
    public Response getHuman(@PathParam("id") final long id) {
        return new GetHumanState.Builder()
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
    public Response createHuman(final Human human) {
        return new PostHumanState.Builder()
                .setModelToCreate(human)
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
    public Response updateHuman(@PathParam("id") long id, final Human human) {
        return new PutHumanState.Builder()
                .setModelToUpdate(human)
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
    public Response deleteHuman(@PathParam("id") final long id) {
        return new DeleteHumanState.Builder()
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .build()
                .execute();
    }
}
