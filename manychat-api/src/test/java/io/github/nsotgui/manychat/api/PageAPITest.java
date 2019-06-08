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

import io.github.nsotgui.manychat.BotField;
import io.github.nsotgui.manychat.CustomField;
import io.github.nsotgui.manychat.Tag;
import io.github.nsotgui.manychat.Widget;
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

    @Test
    public void getTags() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/get_tags.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_TAGS;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        List<Tag> tags = manyChatAPIClient.getTags();

        assertEquals(2, tags.size());
        assertEquals(1, tags.get(0).getId().intValue());
        assertEquals("has_done_this", tags.get(0).getName());
        assertEquals(2, tags.get(1).getId().intValue());
        assertEquals("has_done_that", tags.get(1).getName());
        mockServer.verify();
    }

    @Test
    public void createTag() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/create_tag.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_CREATE_TAG;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        Tag tag = new Tag();
        tag.setName("My new tag");
        Tag createdTag = manyChatAPIClient.createTag(tag);

        assertEquals(tag.getName(), createdTag.getName());
        assertEquals(1502, createdTag.getId().intValue());
        mockServer.verify();
    }

    @Test
    public void getWidgets() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/get_widgets.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_WIDGETS;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        List<Widget> widgets = manyChatAPIClient.getWidgets();

        assertEquals(2, widgets.size());
        assertEquals(1, widgets.get(0).getId().intValue());
        assertEquals("Widget1", widgets.get(0).getName());
        assertEquals("customer_chat", widgets.get(0).getType());
        assertEquals(2, widgets.get(1).getId().intValue());
        assertEquals("Widget2", widgets.get(1).getName());
        assertEquals("landing", widgets.get(1).getType());
        mockServer.verify();
    }

    @Test
    public void getBotFields() throws URISyntaxException {
        Resource resource = new InputStreamResource(this.getClass().getResourceAsStream("/manychat-api-responses/get_bot_fields.json"));
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_BOT_FIELDS;

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(endpoint)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resource)
                );

        List<BotField> botFields = manyChatAPIClient.getBotFields();
        assertEquals(2, botFields.size());
        assertEquals(1, botFields.get(0).getId().intValue());
        assertEquals("BOT_FIELD_1", botFields.get(0).getName());
        assertEquals("number", botFields.get(0).getType());
        assertEquals("My Description", botFields.get(0).getDescription());
        assertEquals("0", botFields.get(0).getValue());
        assertEquals(2, botFields.get(1).getId().intValue());
        assertEquals("BOT_FIELD_2", botFields.get(1).getName());
        assertEquals("text", botFields.get(1).getType());
        assertEquals("", botFields.get(1).getDescription());
        assertEquals("My test", botFields.get(1).getValue());
        mockServer.verify();
    }
}