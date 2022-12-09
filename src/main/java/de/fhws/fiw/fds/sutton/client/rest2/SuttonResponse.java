/*
 * Copyright (c) peter.braun@fhws.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.sutton.client.rest2;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkConverter;
import de.fhws.fiw.fds.sutton.client.converters.DateTimeConverter;
import de.fhws.fiw.fds.sutton.client.utils.Header;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import okhttp3.Headers;
import okhttp3.Response;

public class SuttonResponse {
	public static final int OK = 200;

	public static final int CREATED = 201;

	public static final int NO_CONTENT = 204;

	public static final int NOT_MODIFIED = 304;

	public static final int BAD_REQUEST = 400;

	public static final int UNAUTHORIZED = 401;

	public static final int FORBIDDEN = 403;

	public static final int NOT_FOUND = 404;

	public static final int METHOD_NOT_ALLOWED = 405;

	public static final int NOT_ACCEPTABLE = 406;

	public static final int CONFLICT = 409;

	public static final int PRECONDITION_FAILED = 412;

	public static final int UNSUPPORTED_MEDIA_TYPE = 415;

	public static final int UNPROCESSABLE_ENTITY = 422;

	public static final int INTERNAL_SERVER_ERROR = 500;

	public static final int SERVICE_UNAVAILABLE = 503;

	private static final String HEADER_TOTALNUMBEROFRESULTS = "X-totalnumberofresults";

	private static final String HEADER_NUMBEROFRESULTS = "X-numberofresults";

	private SuttonRequest requestForThisResponse;

	private int statusCode;

	private Map<String, Link> mapRelationTypeToLink;

	private Response response;

	private byte[] responseData;

	private Map<String, List<String>> responseHeaders;

	protected SuttonResponse(final Response response, final SuttonRequest lastRequest) {
		this.mapRelationTypeToLink = new HashMap<>();
		this.requestForThisResponse = lastRequest;
		this.response = response;
		processResponse();
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public final String getLocationUri() {
		return getHeaderString("Location");
	}

	public final boolean containsLink(final String relationType) {
		return this.mapRelationTypeToLink.containsKey(relationType);
	}

	public final byte[] getResponseBytes() {
		return this.responseData;
	}

	public final <T> T readEntity(final Class<T> clazz) {
		final Genson genson = new GensonBuilder()
				.withConverters(new ClientLinkConverter(), new DateTimeConverter()).create();
		return genson.deserialize(this.responseData, clazz);
	}

	public final <T> T readEntities(GenericType<T> toType) {
		final Genson genson = new GensonBuilder()
				.withConverters(new ClientLinkConverter(), new DateTimeConverter()).create();

		return genson.deserialize(this.responseData, toType);
	}

	public final String getResponseString() {
		return new String(this.responseData);
	}

	public final int getTotalNumberOfResults() {
		return getHeaderInt(HEADER_TOTALNUMBEROFRESULTS);
	}

	public final int getNumberOfResults() {
		return getHeaderInt(HEADER_NUMBEROFRESULTS);
	}

	public final List<Header> getHeaders(String header) {
		List<String> responseHeaders = this.responseHeaders.get(header);

		List<Header> headers = new LinkedList<>();

		if (responseHeaders != null) {
			for (String value : responseHeaders) {
				headers.add(new Header(header, value));
			}
		}

		return headers;
	}

	public List<Link> getLinks() {
		return new LinkedList(this.mapRelationTypeToLink.values());
	}

	public final Link getLink(final String relationType) {
		return this.mapRelationTypeToLink.get(relationType);
	}

	public final SuttonRequest createRequestFromHeaderLink(final String headerRelationType) {
		return createRequest(getLink(headerRelationType));
	}

	public final SuttonRequest createRequestFromBodyLink(final String relationType) {
		return createRequest(getLinkFromBody(relationType));
	}

	private Link getLinkFromBody(String relationType) {
		final Genson genson = new GensonBuilder()
				.withConverters(new ClientLinkConverter(), new DateTimeConverter()).create();
		Map<String, Object> map = genson.deserialize(this.responseData, Map.class);
		Map<String, String> linkMap = (Map<String, String>) map.get(relationType);
		Link link = new Link(linkMap.get("href"), linkMap.get("type"), linkMap.get("rel"));
		return link;
	}

	public final SuttonRequest createRequest(final Link link) {
		SuttonRequest nextRequest = requestForThisResponse;

		if (link != null) {
			nextRequest.setUriTemplate(link.getUrl());
			nextRequest.setMediaType(link.getMediaType());
		}

		return nextRequest;
	}

	protected final void processResponse() {
		try {
			readStatusCode();
			readAllHeaders();
			readAllLinks();
			readResponseBody();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	protected void readStatusCode() {
		this.statusCode = this.response.code();
	}

	protected void readAllHeaders() {
		final Headers headers = this.response.headers();
		this.responseHeaders = headers.toMultimap();
	}

	protected final void readAllLinks() {
		final List<String> linkHeaders = this.responseHeaders.get("Link");

		if (linkHeaders != null) {
			for (final String linkHeader : linkHeaders) {
				final Link link = Link.parseFromHttpHeader(linkHeader);
				this.mapRelationTypeToLink.put(link.getRelationType(), link);
			}
		}
	}

	protected void readResponseBody() throws IOException {
		this.responseData = this.response.body().bytes();
	}

	private String getHeaderString(String headerName) {
		List<String> allValues = this.responseHeaders.get(headerName);
		String value = allValues.get(0);
		return value;
	}

	private int getHeaderInt(String headerName) {
		String value = getHeaderString(headerName);
		return Integer.parseInt(value);
	}
}
