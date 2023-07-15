package de.fhws.fiw.fds.suttondemoHibernate.client.web;

import com.owlike.genson.GenericType;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemoHibernate.client.models.PersonClientModel;

import java.io.IOException;
import java.util.List;

public class PersonWebClient {

    private GenericWebClient<PersonClientModel> client;

    public PersonWebClient() {
        this.client = new GenericWebClient<>();
    }

    public PersonWebResponse getSinglePerson(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PersonClientModel.class));
    }

    public PersonWebResponse getCollectionOfPersons(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url,
                new GenericType<List<PersonClientModel>>() {

                }));
    }

    public PersonWebResponse postNewPerson(String url, PersonClientModel person)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, person));
    }

    public PersonWebResponse putPerson(String url, PersonClientModel person) throws IOException {
        return createResponse(this.client.sendPutRequest(url, person));
    }

    public PersonWebResponse deletePerson(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PersonWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    private PersonWebResponse createResponse(WebApiResponse<PersonClientModel> response) {
        return new PersonWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }

}
