/*
 * Copyright 2019 Nicolas Sotgui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.nsotgui.manychat.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public class GenericAPITest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private ManyChatAPIClient manyChatAPIClient;
    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        this.manyChatAPIClient = new ManyChatAPIClientImpl(restTemplate, "<token>");
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getError() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/error.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_CUSTOM_FIELDS;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        exception.expect(RestClientException.class);
        exception.expectMessage("Token is required");

        manyChatAPIClient.getCustomFields();

        mockServer.verify();
    }
}