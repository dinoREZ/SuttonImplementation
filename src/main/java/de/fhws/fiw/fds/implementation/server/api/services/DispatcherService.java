package de.fhws.fiw.fds.implementation.server.api.services;

import de.fhws.fiw.fds.implementation.server.api.rateLimiting.AnyApiKeyRateLimiter;
import de.fhws.fiw.fds.implementation.server.api.states.dispatcher.GetDispatcher;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class DispatcherService extends AbstractService {

    @GET
    public Response getDefault() {
        return new GetDispatcher.Builder()
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .build()
                .execute();
    }
}
