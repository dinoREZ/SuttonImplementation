package de.fhws.fiw.fds.implementation.server.database.hibernate;

import de.fhws.fiw.fds.implementation.server.api.models.Human;
import de.fhws.fiw.fds.implementation.server.database.HumanDao;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.HumanDaoHibernate;
import de.fhws.fiw.fds.implementation.server.database.hibernate.dao.HumanDaoHibernateImpl;
import de.fhws.fiw.fds.implementation.server.database.hibernate.models.HumanDB;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.stream.Collectors;

public class HumanDaoAdapter implements HumanDao {

    private final HumanDaoHibernate dao = new HumanDaoHibernateImpl();

    @Override
    public NoContentResult create(Human model) {
        final HumanDB dbModel = createFrom(model);
        final NoContentResult returnValue = this.dao.create(dbModel);
        model.setId(dbModel.getId());
        return returnValue;
    }

    @Override
    public SingleModelResult<Human> readById(long id) {
        SingleModelHibernateResult<HumanDB> result = this.dao.readById(id);
        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            final SingleModelResult<Human> returnValue = new SingleModelResult<>( );
            returnValue.setError( );
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult()));
        }
    }

    @Override
    public CollectionModelResult<Human> readAll(SearchParameter searchParameter) {
        // TODO
        return null;
    }

    @Override
    public CollectionModelResult<Human> readByQuery(String firstName, String lastName) {
        CollectionModelHibernateResult<HumanDB> result = dao.readByQuery(firstName, lastName);

        CollectionModelResult<Human> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();

        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(this::createFrom).collect(Collectors.toList()));
        }
        return returnValue;
    }

    @Override
    public NoContentResult update(Human model) {
        return dao.update(createFrom(model));
    }

    @Override
    public NoContentResult delete(long id) {
        return dao.delete(id);
    }

    private HumanDB createFrom(Human model) {
        final HumanDB returnValue = new HumanDB();
        returnValue.setId(model.getId());
        returnValue.setFirstName(model.getFirstName());
        returnValue.setLastName(model.getLastName());
        return returnValue;
    }

    private Human createFrom(HumanDB model) {
        final Human returnValue = new Human();
        returnValue.setId(model.getId());
        returnValue.setFirstName(model.getFirstName());
        returnValue.setLastName(model.getLastName());
        return returnValue;
    }
}
