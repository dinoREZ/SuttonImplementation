package de.fhws.fiw.fds.implementation;

import de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller;
import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart {

    public final static String CONTEXT_PATH = "test";

    public Start() {
        super(CONTEXT_PATH);
    }

    public static void main(String[] args) throws Exception {
        new Start().startTomcat();
    }

    @Override
    protected AbstractDatabaseInstaller getInstaller() {
        return new DatabaseInstaller();
    }
}
