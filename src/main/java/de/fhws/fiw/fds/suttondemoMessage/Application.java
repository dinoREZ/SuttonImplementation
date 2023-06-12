package de.fhws.fiw.fds.suttondemoMessage;

import de.fhws.fiw.fds.suttondemoMessage.api.service.MessageService;
import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("message_api")
public class Application extends AbstractApplication {
    @Override
    protected Set<Class<?>> getServiceClasses() {
        final Set<Class<?>> returnValue = new HashSet<>();
        returnValue.add(MessageService.class);
        return returnValue;
    }
}
