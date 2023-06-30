package de.fhws.fiw.fds.suttondemoHibernate.server.api.states.locations;


import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;

public class PostNewLocation extends AbstractPostState<Location>
{
	public PostNewLocation(final Builder builder )
	{
		super( builder );
	}

	@Override
	protected void authorizeRequest() {

	}

	@Override protected NoContentResult saveModel( )
	{
		return DaoFactory.getInstance( ).getLocationDao( ).create( this.modelToStore );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class Builder extends AbstractPostStateBuilder<Location>
	{
		@Override public AbstractState build( )
		{
			return new PostNewLocation( this );
		}
	}
}
