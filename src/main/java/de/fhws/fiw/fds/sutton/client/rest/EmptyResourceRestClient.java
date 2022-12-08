package de.fhws.fiw.fds.sutton.client.rest;

import java.util.List;

import com.owlike.genson.GenericType;

import de.fhws.fiw.fds.sutton.client.model.EmptyResource;
import de.fhws.fiw.fds.sutton.client.web.HeaderMap;

public class EmptyResourceRestClient extends AbstractResourceRestClient<EmptyResource> {
	public EmptyResourceRestClient(final HeaderMap headers) {
		super(headers);
	}

	@Override
	protected String defineUrl() {
		return "";
	}

	@Override
	protected Class<EmptyResource> defineClassTypeForSingleResource() {
		return EmptyResource.class;
	}

	@Override
	protected GenericType<List<EmptyResource>> defineClassTypeForCollectionResource() {
		return new GenericType<List<EmptyResource>>() {
		};
	}
}