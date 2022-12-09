package de.fhws.fiw.fds.sutton.client.rest;

import java.io.IOException;
import de.fhws.fiw.fds.sutton.client.IBaseUrl;
import de.fhws.fiw.fds.sutton.client.model.EmptyResource;
import de.fhws.fiw.fds.sutton.client.utils.HeaderMap;

public class DispatcherRestClient {
	private final String dispatcherUrl = IBaseUrl.BASE_URL;

	private final GenericRestClient<EmptyResource> restClient;

	public DispatcherRestClient() {
		this.restClient = new GenericRestClient(defineDefaultHeaders());
	}

	private HeaderMap defineDefaultHeaders() {
		final HeaderMap headers = new HeaderMap();
		headers.addHeader("Accept", "application/json");

		return headers;
	}

	public DispatcherRestClient(final HeaderMap headers) {
		this.restClient = new GenericRestClient<>(headers);
	}

	public RestApiResponse<EmptyResource> triggerDispatcherRequest() throws IOException {
		return this.restClient.sendGetSingleRequest(this.dispatcherUrl);
	}
}
