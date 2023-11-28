package de.fhws.fiw.fds.implementation.server;

import de.fhws.fiw.fds.sutton.server.AbstractStart;
import de.fhws.fiw.fds.sutton.server.database.hibernate.DatabaseInstaller;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Start extends AbstractStart {

    public final static String CONTEXT_PATH = "test";

    public static void main(String[] args) throws Exception {
        new Start().startTomcat();
    }

    @Override
    protected String contextPath() {
        return CONTEXT_PATH;
    }
}
