package de.fhws.fiw.fds.suttondemoHibernate.server.api.states.locations;

import de.fhws.fiw.fds.suttondemoHibernate.Start;

public interface LocationUri
{
	String PATH_ELEMENT = "locations";
	String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
	String REL_PATH_ID = REL_PATH + "/{id}";
}
