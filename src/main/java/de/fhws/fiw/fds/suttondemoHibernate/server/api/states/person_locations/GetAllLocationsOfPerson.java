package de.fhws.fiw.fds.suttondemoHibernate.server.api.states.person_locations;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemoHibernate.server.DaoFactory;
import de.fhws.fiw.fds.suttondemoHibernate.server.PersonLocationDao;
import de.fhws.fiw.fds.suttondemoHibernate.server.api.models.Location;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;
import java.util.function.Predicate;


public class GetAllLocationsOfPerson extends AbstractGetCollectionRelationState<Location> {
    public GetAllLocationsOfPerson(final Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected void defineHttpResponseBody() {
        this.responseBuilder.entity(new GenericEntity<Collection<Location>>(this.result.getResult()) {
        });
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(PersonLocationUri.REL_PATH,
                PersonLocationRelTypes.CREATE_LOCATION,
                getAcceptRequestHeader(),
                this.primaryId);

        if (this.query.isShowAll()) {
            addLink(PersonLocationUri.REL_PATH_SHOW_ONLY_LINKED,
                    PersonLocationRelTypes.GET_ALL_LINKED_LOCATIONS,
                    getAcceptRequestHeader(),
                    this.primaryId);
        } else {
            addLink(PersonLocationUri.REL_PATH_SHOW_ALL,
                    PersonLocationRelTypes.GET_ALL_LOCATIONS,
                    getAcceptRequestHeader(),
                    this.primaryId);
        }
    }

    public static class AllLocations extends AbstractRelationQuery<Location> {
        private final PersonLocationDao dao;

        public AllLocations(final long primaryId, final boolean showAll) {
            super(primaryId, showAll);
            this.dao = DaoFactory.getInstance().getPersonLocationDao();
        }

        @Override
        protected CollectionModelResult<Location> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            if (showAll) {
                return this.dao.readAllByPredicate(this.primaryId, all());
            } else {
                return this.dao.readAllByPredicate(this.primaryId, all());
            }
        }
    }

    public static class FilterLocationsByName extends AbstractRelationQuery<Location> {
        private final PersonLocationDao dao;
        private final String cityName;

        public FilterLocationsByName(final long primaryId, final boolean showAll, final String cityName) {
            super(primaryId, showAll);
            this.cityName = cityName;
            this.dao = DaoFactory.getInstance().getPersonLocationDao();
        }

        @Override
        protected CollectionModelResult<Location> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            if (showAll) {
                return this.dao.readAllByPredicate(this.primaryId, byCityName());
            } else {
                return this.dao.readByPredicate(this.primaryId, byCityName());
            }
        }

        protected Predicate<Location> byCityName() {
            return l -> StringUtils.isEmpty(cityName) || l.getCityName().equalsIgnoreCase(cityName);
        }

    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<Location> {
        @Override
        public AbstractState build() {
            return new GetAllLocationsOfPerson(this);
        }
    }
}
