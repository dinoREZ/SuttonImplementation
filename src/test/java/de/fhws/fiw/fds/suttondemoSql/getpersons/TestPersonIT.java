// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.suttondemoSql.getpersons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import de.fhws.fiw.fds.suttondemoSql.client.web.SimplePersonResponse;
import de.fhws.fiw.fds.suttondemoSql.client.web.SimplePersonWebClientUsingIds;
import de.fhws.fiw.fds.suttondemoSql.models.PersonClientModel;

public class TestPersonIT {

    private final static String BASE_URL = "http://localhost:8080/sd/api/persons";

    private SimplePersonWebClientUsingIds client;

    @Before
    public void set_up() throws IOException {
        this.client = new SimplePersonWebClientUsingIds(BASE_URL);
        resetDatabaseOnServer();
    }

    @Test
    public void test_get_persons_must_be_empty() throws IOException {
        final SimplePersonResponse response = this.client.getCollectionOfPersons();
        assertEquals(200, response.getLastStatusCode());
        assertEquals(0, response.getResponseData().size());
    }

    @Test
    public void test_post_person_and_get_later() throws IOException {
        final PersonClientModel person = new PersonClientModel();
        person.setFirstName("Peter");
        person.setLastName("Braun");
        person.setEmailAddress("peter.braun@fhws.de");
        person.setBirthDate(LocalDate.of(1970, 6, 22));

        final SimplePersonResponse response = this.client.postNewPerson(person);

        assertEquals(201, response.getLastStatusCode());
        assertTrue(response.getLocationHeader().isPresent());
        assertEquals(BASE_URL + "/1", response.getLocationHeader().get());
    }

    private void resetDatabaseOnServer() throws IOException {
        this.client.resetDatabaseOnServer();
    }
}
