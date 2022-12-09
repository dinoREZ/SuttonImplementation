package de.fhws.fiw.fds.suttondemo.dispatcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;
import org.junit.Ignore;
import org.junit.Test;
import de.fhws.fiw.fds.sutton.client.AbstractTest;
import de.fhws.fiw.fds.sutton.client.model.EmptyResource;
import de.fhws.fiw.fds.sutton.client.rest.DispatcherRestClient;
import de.fhws.fiw.fds.sutton.client.rest.EmptyResourceRestClient;
import de.fhws.fiw.fds.sutton.client.rest.RestApiResponse;
import de.fhws.fiw.fds.sutton.client.utils.HeaderMap;
import de.fhws.fiw.fds.sutton.client.utils.HeaderMapUtils;

@Ignore
public class DispatcherTestIT extends AbstractTest<EmptyResource, EmptyResourceRestClient> {
	@Test
	public void test_200() throws IOException {
		final DispatcherRestClient dispatcherRestClient = new DispatcherRestClient();
		final RestApiResponse<EmptyResource> response =
				dispatcherRestClient.triggerDispatcherRequest();

		assertEquals(200, response.getLastStatusCode());
	}

	@Test
	public void test_hypermedia() throws IOException {
		final DispatcherRestClient dispatcherRestClient = new DispatcherRestClient();
		final RestApiResponse<EmptyResource> response =
				dispatcherRestClient.triggerDispatcherRequest();

		assertNotNull(response.getParsedLinkHeader("self"));
		assertNotNull(response.getParsedLinkHeader("getAllStudents"));
		assertNotNull(response.getParsedLinkHeader("getAllStudyTrips"));
	}

	@Test
	public void test_correct_media_type() throws IOException {
		final DispatcherRestClient dispatcherRestClient =
				new DispatcherRestClient(HeaderMapUtils.withAcceptJson());
		final RestApiResponse<EmptyResource> response =
				dispatcherRestClient.triggerDispatcherRequest();

		assertEquals(200, response.getLastStatusCode());
	}

	@Test
	public void test_incorrect_media_type() throws IOException {
		final DispatcherRestClient dispatcherRestClient =
				new DispatcherRestClient(HeaderMapUtils.withAcceptXml());
		final RestApiResponse<EmptyResource> response =
				dispatcherRestClient.triggerDispatcherRequest();

		assertEquals(406, response.getLastStatusCode());
	}

	@Override
	protected EmptyResourceRestClient newRestClient(final HeaderMap headers) {
		return new EmptyResourceRestClient(headers);
	}
}
