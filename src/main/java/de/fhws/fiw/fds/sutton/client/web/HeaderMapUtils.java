package de.fhws.fiw.fds.sutton.client.web;

public class HeaderMapUtils {
	public static HeaderMap empty() {
		return new HeaderMap();
	}

	public static HeaderMap withAcceptJson() {
		final HeaderMap headers = new HeaderMap();
		headers.addHeader("Accept", "application/json");

		return headers;
	}

	public static HeaderMap withAcceptXml() {
		final HeaderMap headers = new HeaderMap();
		headers.addHeader("Accept", "application/xml");

		return headers;
	}

	public static HeaderMap withContentTypeJson() {
		final HeaderMap headers = new HeaderMap();
		headers.addHeader("Content-Type", "application/json");

		return headers;
	}

	public static HeaderMap withContentTypeXml() {
		final HeaderMap headers = new HeaderMap();
		headers.addHeader("Content-Type", "application/xml");

		return headers;
	}
}