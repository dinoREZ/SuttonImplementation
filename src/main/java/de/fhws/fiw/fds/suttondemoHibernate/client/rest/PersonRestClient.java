package de.fhws.fiw.fds.suttondemoHibernate.client.rest;

import com.owlike.genson.GenericType;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemoHibernate.client.models.PersonClientModel;

import java.io.IOException;
import java.util.List;

public class PersonRestClient {

    private static final String BASE_URL = "http://localhost:8080/exam01/api";

    private GenericWebClient<PersonClientModel> client;

    public PersonRestClient() {
        this.client = new GenericWebClient<>();
    }

    public PersonRestResponse start() throws IOException {
        return createResponse(this.client.sendGetSingleRequest(BASE_URL));

    }

    public PersonRestResponse readSinglePerson(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PersonClientModel.class));
    }

    public PersonRestResponse readCollectionOfPersons(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url,
                new GenericType<List<PersonClientModel>>() {

                }));
    }

    public PersonRestResponse createNewPerson(String url, PersonClientModel person)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, person));
    }

    public PersonRestResponse updatePerson(String url, PersonClientModel person) throws IOException {
        return createResponse(this.client.sendPutRequest(url, person));
    }

    public PersonRestResponse deletePerson(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public PersonRestResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    private PersonRestResponse createResponse(WebApiResponse<PersonClientModel> response) {
        return new PersonRestResponse(response);
    }

}
