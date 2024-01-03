package de.fhws.fiw.fds.problem;

import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ApplicationPath;
import java.util.Set;

@ApplicationPath("abc")
public class Problem extends AbstractApplication {

    @Override
    protected AbstractBinder registerDependencyInjectionBinder() {
        return new AbstractBinder() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected Set<Class<?>> getServiceClasses() {
        return null;
    }

}