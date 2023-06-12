package de.fhws.fiw.fds.suttondemoSql.client.web;

import java.io.IOException;
import java.util.List;
import com.owlike.genson.GenericType;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.suttondemoSql.models.PersonClientModel;

public class SimplePersonWebClient {

    private GenericWebClient<PersonClientModel> client;

    public SimplePersonWebClient() {
        this.client = new GenericWebClient<>();
    }

    public SimplePersonResponse getSinglePerson(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, PersonClientModel.class));
    }

    public SimplePersonResponse getCollectionOfPersons(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url,
                new GenericType<List<PersonClientModel>>() {

                }));
    }

    public SimplePersonResponse postNewPerson(String url, PersonClientModel person)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, person));
    }

    public SimplePersonResponse putPerson(String url, PersonClientModel person) throws IOException {
        return createResponse(this.client.sendPutRequest(url, person));
    }

    public SimplePersonResponse deletePerson(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public SimplePersonResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "resetdatabase"));
    }

    private SimplePersonResponse createResponse(WebApiResponse<PersonClientModel> response) {
        return new SimplePersonResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }
}
