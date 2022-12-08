package de.fhws.fiw.fds.sutton.client.web;

import okhttp3.Headers;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class WebApiResponse<T extends AbstractClientModel> {
	private final Collection<T> responseData;

	private final Headers responseHeaders;

	private final int lastStatusCode;

	public WebApiResponse(final int lastStatusCode) {
		this(Collections.EMPTY_LIST, Headers.of(), lastStatusCode);
	}

	public WebApiResponse(final int lastStatusCode, final Headers headers) {
		this(Collections.EMPTY_LIST, headers, lastStatusCode);
	}

	public WebApiResponse(final T responseData, final Headers headers, final int lastStatusCode) {
		this(Optional.of(responseData), headers, lastStatusCode);
	}

	public WebApiResponse(final Optional<T> responseData, final Headers headers, final int lastStatusCode) {
		this(convertToList(responseData), headers, lastStatusCode);
	}

	public WebApiResponse(final Collection<T> responseData, final Headers headers, final int lastStatusCode) {
		this.responseData = responseData;
		this.responseHeaders = headers;
		this.lastStatusCode = lastStatusCode;
	}

	public Collection<T> getResponseData() {
		return responseData;
	}

	public Headers getResponseHeaders() {
		return responseHeaders;
	}

	public Optional<T> getFirstResponse() {
		return this.responseData.stream().findFirst();
	}

	public int getLastStatusCode() {
		return lastStatusCode;
	}

	private static <T> Collection<T> convertToList(final Optional<T> object) {
		return object.isPresent() ? Collections.singletonList(object.get()) : Collections.emptyList();
	}
}
