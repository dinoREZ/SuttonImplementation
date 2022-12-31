package de.fhws.fiw.fds.sutton.server.utils;

import java.net.URI;
import javax.ws.rs.core.Link;

public class UriHelper {
	public static int getLastPathElementAsId(final Link link) {
		if (link != null) {
			return getLastPathElementAsId(link.getUri());
		}
		else {
			return 0;
		}
	}

	public static int getLastPathElementAsId(final URI uri) {
		final String path = uri.getPath();
		final int lastSlash = path.lastIndexOf("/");
		return Integer.parseInt(path.substring(lastSlash + 1));
	}
}
