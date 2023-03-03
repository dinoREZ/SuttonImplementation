package de.fhws.fiw.fds.messageSuttondemo;

import de.fhws.fiw.fds.messageSuttondemo.api.service.MessageService;
import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Application extends AbstractApplication {
    @Override
    protected Set<Class<?>> getServiceClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();
        returnValue.add(MessageService.class);
        return returnValue;
    }
}
