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

package de.fhws.fiw.fds.sutton.server.ittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestGetDispatcherIT {
    @Test
    public void get_dispatcher_200() {
        assertTrue(true);
    }

    @Test
    public void get_person_collection_empty() throws Exception {
        final HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegment("sd")
                .addPathSegment("api")
                .addPathSegment("persons")
                .build();

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        final Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
        assertEquals("[\n]", response.body().string());
    }
}