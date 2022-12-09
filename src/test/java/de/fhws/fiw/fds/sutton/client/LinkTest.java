package de.fhws.fiw.fds.sutton.client;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import de.fhws.fiw.fds.sutton.client.utils.Link;

public class LinkTest {
	@Test
	public void test_parse_link() {
		final String linkHeader = "<http://localhost:8080/api>; rel=\"self\"; type=\"*.*\"";
		final Link link = Link.parseFromHttpHeader(linkHeader);
		assertEquals("http://localhost:8080/api", link.getUrl());
		assertEquals("self", link.getRelationType());
		assertEquals("*.*", link.getMediaType());
	}

}
