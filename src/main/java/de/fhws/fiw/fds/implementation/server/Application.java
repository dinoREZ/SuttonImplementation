package de.fhws.fiw.fds.implementation.server;

import de.fhws.fiw.fds.implementation.server.api.services.DispatcherService;
import de.fhws.fiw.fds.implementation.server.api.services.HumanService;
import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Application extends AbstractApplication {
    @Override
    protected Set<Class<?>> getServiceClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(DispatcherService.class);
        returnValue.add(HumanService.class);

        // TODO add return values

        return returnValue;
    }
}
