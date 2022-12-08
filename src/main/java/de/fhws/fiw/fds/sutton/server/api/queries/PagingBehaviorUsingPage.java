package de.fhws.fiw.fds.sutton.server.api.queries;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

public class PagingBehaviorUsingPage<T extends AbstractModel> extends PagingBehavior<T> {
	public static final String QUERY_PARAM_PAGE = "page";
	private static final int DEFAULT_PAGE_SIZE = 10;

	protected String pageQueryParamName = QUERY_PARAM_PAGE;

	protected int pageNumber;

	public PagingBehaviorUsingPage(final String pageQueryParamName, final int pageNumber) {
		this.pageQueryParamName = pageQueryParamName;
		setPageNumber(pageNumber);
	}

	public PagingBehaviorUsingPage(final int pageNumber) {
		setPageNumber(pageNumber);
	}

	@Override
	public int getOffset() {
		return (this.pageNumber - 1) * DEFAULT_PAGE_SIZE;
	}

	@Override
	public int getSize() {
		return DEFAULT_PAGE_SIZE;
	}

	@Override
	protected boolean hasNextLink(final CollectionModelResult<?> result) {
		return this.pageNumber * DEFAULT_PAGE_SIZE < result.getTotalNumberOfResult();
	}

	@Override
	protected boolean hasPrevLink() {
		return this.pageNumber > 1;
	}

	@Override
	protected URI getSelfUri(final UriInfo uriInfo) {
		final UriBuilder uriBuilder = createUriBuilder(uriInfo);
		return uriBuilder.build(this.pageNumber);
	}

	@Override
	protected URI getPrevUri(final UriInfo uriInfo) {
		final UriBuilder uriBuilder = createUriBuilder(uriInfo);
		return uriBuilder.build(this.pageNumber - 1);
	}

	@Override
	protected URI getNextUri(final UriInfo uriInfo, final CollectionModelResult<?> result) {
		final UriBuilder uriBuilder = createUriBuilder(uriInfo);
		return uriBuilder.build(this.pageNumber + 1);
	}

	private void setPageNumber(final int pageNumber) {
		this.pageNumber = Math.max(1, pageNumber);
	}

	private UriBuilder createUriBuilder(final UriInfo uriInfo) {
		return uriInfo.getRequestUriBuilder()
				.replaceQueryParam(getPageParamName(), getQueryParamPageAsTemplate());
	}

	private String getPageParamName() {
		return this.pageQueryParamName;
	}

	private final String getQueryParamPageAsTemplate() {
		return "{" + getPageParamName() + "}";
	}

}
