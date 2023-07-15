package de.fhws.fiw.fds.sutton.server.api.states;


import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractStateTest {
    @Test
    public void testExecute_buildInternal_is_called_no_exception() throws Exception {
        final AbstractState stateUnderTest = new DemoStateReturns200Ok();
        final Response response = stateUnderTest.execute();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testExecute_buildInternal_is_called_web_exception() throws Exception {
        assertThrows(WebApplicationException.class, () -> {
            final AbstractState stateUnderTest = new DemoStateThrowsWebException();
            stateUnderTest.execute();
        });
    }

    @Test
    public void testExecute_buildInternal_is_called_illegal_argument_exception() throws Exception {
        final AbstractState stateUnderTest = new DemoStateThrowsIllegalArgumentException();
        final Response response = stateUnderTest.execute();
        assertEquals(500, response.getStatus());
    }
}
