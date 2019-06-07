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

import io.github.nsotgui.manychat.CustomField;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public class PageAPITest {

    private ManyChatAPIClient manyChatAPIClient;
    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        this.manyChatAPIClient = new ManyChatAPIClientImpl(restTemplate, "<token>");
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getCustomFields() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/get_custom_fields.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_CUSTOM_FIELDS;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        List<CustomField> customFields = manyChatAPIClient.getCustomFields();
        assertEquals(3, customFields.size());
        assertEquals(42, customFields.get(0).getId().intValue());
        assertEquals("ANSWER_TO_LIFE", customFields.get(0).getName());
        assertEquals("text", customFields.get(0).getType());
        assertEquals("description", customFields.get(0).getDescription());
        assertEquals(7, customFields.get(1).getId().intValue());
        assertEquals("RANDOM_NUMBER", customFields.get(1).getName());
        assertEquals("number", customFields.get(1).getType());
        assertEquals("", customFields.get(1).getDescription());
        assertEquals(123, customFields.get(2).getId().intValue());
        assertEquals("RANDOM_BOOLEAN", customFields.get(2).getName());
        assertEquals("boolean", customFields.get(2).getType());
        assertEquals("", customFields.get(2).getDescription());
        mockServer.verify();
    }
}