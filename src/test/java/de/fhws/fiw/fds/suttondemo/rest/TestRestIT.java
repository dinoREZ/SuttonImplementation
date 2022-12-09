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

package de.fhws.fiw.fds.suttondemo.rest;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import com.owlike.genson.GenericType;
import de.fhws.fiw.fds.sutton.client.rest2.SuttonRequest;
import de.fhws.fiw.fds.sutton.client.rest2.SuttonRequest.HttpVerb;
import de.fhws.fiw.fds.sutton.client.rest2.SuttonResponse;
import de.fhws.fiw.fds.suttondemo.models.PersonClientModel;
import de.fhws.fiw.fds.suttondemo.server.api.states.persons.PersonRelTypes;

public class TestRestIT {

    private static final String BASE_URL = "http://localhost:8080/suttondemo/api/";

    @Test
    public void test_get_dispatcher() throws IOException {
        final SuttonResponse response =
                new SuttonRequest().setUriTemplate(BASE_URL).setHttpVerb(HttpVerb.GET).execute();

        assertEquals(200, response.getStatusCode());
        assertEquals(BASE_URL, response.getLink("self").getUrl());
    }

    @Test
    public void test_get_all_persons_via_dispatcher() throws IOException {
        final SuttonResponse dispatcherResponse =
                new SuttonRequest().setUriTemplate(BASE_URL).setHttpVerb(HttpVerb.GET).execute();

        final SuttonResponse personResponse =
                dispatcherResponse.createRequestFromHeaderLink(PersonRelTypes.GET_ALL_PERSONS)
                        .setHttpVerb(HttpVerb.GET).execute();

        assertEquals(200, personResponse.getStatusCode());

        final List<PersonClientModel> persons =
                personResponse.readEntities(new GenericType<List<PersonClientModel>>() {

                });


        assertEquals(0, persons.size());
    }

}
