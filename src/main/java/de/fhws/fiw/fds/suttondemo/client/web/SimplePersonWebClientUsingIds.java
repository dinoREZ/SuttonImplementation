package de.fhws.fiw.fds.suttondemo.client.web;

import java.io.IOException;
import de.fhws.fiw.fds.suttondemo.models.PersonClientModel;

public class SimplePersonWebClientUsingIds {
    private final SimplePersonWebClient client;
    private final String baseUrl;

    public SimplePersonWebClientUsingIds(String baseUrl) {
        this.client = new SimplePersonWebClient();
        this.baseUrl = addTrailingSlashIfNeeded(baseUrl);
    }

    public SimplePersonResponse getSinglePerson(long id) throws IOException {
        return this.client.getSinglePerson(baseUrl + id);
    }

    public SimplePersonResponse getCollectionOfPersons() throws IOException {
        return this.client.getCollectionOfPersons(this.baseUrl);
    }

    public SimplePersonResponse postNewPerson(PersonClientModel person) throws IOException {
        return this.client.postNewPerson(this.baseUrl, person);
    }

    public SimplePersonResponse putPerson(PersonClientModel person) throws IOException {
        return this.client.putPerson(this.baseUrl + person.getId(), person);
    }

    public SimplePersonResponse resetDatabaseOnServer() throws IOException {
        return this.client.resetDatabaseOnServer(this.baseUrl);
    }

    private String addTrailingSlashIfNeeded(String url) {
        final String urlWithoutTrailingSlash = url.replaceFirst("/*$", "");
        return String.format("%s/", urlWithoutTrailingSlash);
    }
}
