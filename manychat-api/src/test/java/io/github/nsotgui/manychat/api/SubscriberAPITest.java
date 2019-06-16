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
import io.github.nsotgui.manychat.Subscriber;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

public class SubscriberAPITest {

    private ManyChatAPIClient manyChatAPIClient;
    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        this.manyChatAPIClient = new ManyChatAPIClientImpl(restTemplate, "<token>");
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getSubscriberInfo() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/get_subscriber_info.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.SUBSCRIBER_GET_INFO;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("subscriber_id", "123456789");

        mockServer.expect(ExpectedCount.once(),
                requestTo(builder.toUriString()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        Subscriber subscriber = manyChatAPIClient.getSubscriberInfo("123456789");

        assertEquals("123456789", subscriber.getId());
        assertEquals("page123456", subscriber.getPageId());
        assertEquals("John", subscriber.getFirstName());
        assertEquals("Doe", subscriber.getLastName());
        mockServer.verify();
    }

    @Test
    public void setCustomField() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/success.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.SUBSCRIBER_SET_CUSTOM_FIELD;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        CustomField customField = new CustomField();
        customField.setId(1);
        customField.setValue("MyValue");

        manyChatAPIClient.setCustomField("123456789", customField);
        mockServer.verify();
    }
}