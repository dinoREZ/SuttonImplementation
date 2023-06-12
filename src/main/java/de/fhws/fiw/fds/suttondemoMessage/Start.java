package de.fhws.fiw.fds.suttondemoMessage;


import de.fhws.fiw.fds.sutton.server.AbstractStart;

public class Start extends AbstractStart {
    @Override
    protected String contextPath() {
        return "sd";
    }

    public static void main(String[] args) throws Exception {
        new Start().startTomcat();
    }
}
