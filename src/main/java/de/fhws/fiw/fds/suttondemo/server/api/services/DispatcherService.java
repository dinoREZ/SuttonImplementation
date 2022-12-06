package de.fhws.fiw.fds.suttondemo.server.api.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

@Path("")
public class DispatcherService extends AbstractService {
    @GET
    public Response getDispatcher() {
        return Response.ok().build();
    }
}
