package de.fhws.fiw.fds.implementation;

import de.fhws.fiw.fds.implementation.server.api.security.NoAuthNeededAuthenticationProvider;
import de.fhws.fiw.fds.implementation.server.api.services.CourseService;
import de.fhws.fiw.fds.implementation.server.api.services.DispatcherService;
import de.fhws.fiw.fds.implementation.server.api.services.StudentService;
import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;
import de.fhws.fiw.fds.sutton.server.api.security.IAuthenticationProvider;
import de.fhws.fiw.fds.sutton.server.api.security.SuttonAuthenticationProvider;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Application extends AbstractApplication {
    @Override
    protected AbstractBinder registerDependencyInjectionBinder() {
        return new AbstractBinder() {
            @Override
            protected void configure() {
                /*
                 * Configure your DependencyInjection here
                 */
                bind(NoAuthNeededAuthenticationProvider.class).to(IAuthenticationProvider.class);
            }
        };
    }

    @Override
    protected Set<Class<?>> getServiceClasses() {
        ParallelWebappClassLoader classloader = (ParallelWebappClassLoader) this.getClass().getClassLoader();
        classloader.setDelegate(true);

        final Set<Class<?>> returnValue = new HashSet<>();

        returnValue.add(DispatcherService.class);
        returnValue.add(StudentService.class);
        returnValue.add(CourseService.class);

        // TODO add return values

        return returnValue;
    }
}
