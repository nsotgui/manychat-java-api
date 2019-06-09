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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nsotgui.manychat.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Internal implementation of the ManyChat api client
 */
final class ManyChatAPIClientImpl implements ManyChatAPIClient {
    private Logger LOG = LoggerFactory.getLogger(ManyChatAPIClientImpl.class);
    private HttpHeaders headers;
    private RestTemplate restTemplate;

    public ManyChatAPIClientImpl(RestTemplate restTemplate, String apiToken) {
        this.restTemplate = restTemplate;
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + apiToken);
    }

    @Override
    public Tag createTag(Tag tag) throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_CREATE_TAG;
        LOG.info("Creating tag: {} - {}", tag, endpoint);
        HttpEntity<Tag> entity = new HttpEntity<Tag>(tag, headers);
        ResponseEntity<APIResponse<JsonNode>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.POST, entity,
                new ParameterizedTypeReference<APIResponse<JsonNode>>() {
                });

        processResponse(httpResponse);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Tag createdTag = tag;
        try {
            APIResponse<JsonNode> apiResponse = httpResponse.getBody();
            if (apiResponse != null && apiResponse.getData() != null)
                createdTag = mapper.readValue(apiResponse.getData().findValue("tag").toString(), Tag.class);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RestClientException(e.getMessage(), e);
        }
        return createdTag;
    }

    @Override
    public List<Tag> getTags() throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_TAGS;
        LOG.info("Retrieving Tags: {}", endpoint);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<APIResponse<List<Tag>>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<List<Tag>>>() {
                });

        processResponse(httpResponse);

        APIResponse<List<Tag>> apiResponse = httpResponse.getBody();
        List<Tag> tags = new ArrayList<>();
        if (apiResponse != null && apiResponse.getData() != null)
            tags = apiResponse.getData();
        return tags;
    }

    @Override
    public List<Widget> getWidgets() throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_WIDGETS;
        LOG.info("Retrieving Widgets: {}", endpoint);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<APIResponse<List<Widget>>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<List<Widget>>>() {
                });

        processResponse(httpResponse);

        APIResponse<List<Widget>> apiResponse = httpResponse.getBody();
        List<Widget> widgets = new ArrayList<>();
        if (apiResponse != null && apiResponse.getData() != null)
            widgets = apiResponse.getData();
        return widgets;
    }

    public List<CustomField> getCustomFields() throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_CUSTOM_FIELDS;
        LOG.info("Retrieving Custom Fields: {}", endpoint);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<APIResponse<List<CustomField>>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<List<CustomField>>>() {
                });

        processResponse(httpResponse);

        APIResponse<List<CustomField>> apiResponse = httpResponse.getBody();
        List<CustomField> customFields = new ArrayList<>();
        if (apiResponse != null && apiResponse.getData() != null)
            customFields = apiResponse.getData();
        return customFields;
    }

    public List<BotField> getBotFields() throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_BOT_FIELDS;
        LOG.info("Retrieving Bot Fields: {}", endpoint);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<APIResponse<List<BotField>>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<List<BotField>>>() {
                });

        processResponse(httpResponse);

        APIResponse<List<BotField>> apiResponse = httpResponse.getBody();
        List<BotField> botFields = new ArrayList<>();
        if (apiResponse != null && apiResponse.getData() != null)
            botFields = apiResponse.getData();
        return botFields;
    }

    @Override
    public BotField createBotField(BotField botField) throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_CREATE_BOT_FIELD;
        LOG.info("Creating bot field: {} - {}", botField, endpoint);
        HttpEntity<BotField> entity = new HttpEntity<BotField>(botField, headers);
        ResponseEntity<APIResponse<JsonNode>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.POST, entity,
                new ParameterizedTypeReference<APIResponse<JsonNode>>() {
                });

        processResponse(httpResponse);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        BotField createdBotField = botField;
        try {
            APIResponse<JsonNode> apiResponse = httpResponse.getBody();
            if (apiResponse != null && apiResponse.getData() != null)
                createdBotField = mapper.readValue(apiResponse.getData().findValue("field").toString(), BotField.class);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RestClientException(e.getMessage(), e);
        }
        return createdBotField;
    }

    @Override
    public void setBotField(Integer botFieldId, String value) throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_SET_BOT_FIELD;
        LOG.info("Setting value: {} to bot: {} - {}", value, botFieldId, endpoint);
        Properties properties = new Properties();
        properties.setProperty("field_id", botFieldId.toString());
        properties.setProperty("field_value", value);
        HttpEntity<Properties> entity = new HttpEntity<Properties>(properties, headers);
        ResponseEntity<APIResponse<JsonNode>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.POST, entity,
                new ParameterizedTypeReference<APIResponse<JsonNode>>() {
                });

        processResponse(httpResponse);
    }

    @Override
    public void setBotField(String botFieldName, String value) throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_SET_BOT_FIELD_BY_NAME;
        LOG.info("Setting value: {} to bot: {} - {}", value, botFieldName, endpoint);
        Properties properties = new Properties();
        properties.setProperty("field_name", botFieldName);
        properties.setProperty("field_value", value);
        HttpEntity<Properties> entity = new HttpEntity<Properties>(properties, headers);
        ResponseEntity<APIResponse<JsonNode>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.POST, entity,
                new ParameterizedTypeReference<APIResponse<JsonNode>>() {
                });

        processResponse(httpResponse);
    }

    @Override
    public Subscriber getSubscriberInfo(String subscriberId) throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.SUBSCRIBER_GET_INFO;
        LOG.info("Retrieving subscriber: {} - {}", subscriberId, endpoint);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("subscriber_id", subscriberId);

        ResponseEntity<APIResponse<Subscriber>> httpResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<Subscriber>>() {
                });

        processResponse(httpResponse);

        APIResponse<Subscriber> apiResponse = httpResponse.getBody();
        Subscriber subscriber = null;
        if (apiResponse != null && apiResponse.getData() != null)
            subscriber = apiResponse.getData();
        return subscriber;
    }

    private void processResponse(ResponseEntity<? extends APIResponse> httpResponse) {
        LOG.debug("Received response: {}", httpResponse.toString());

        APIResponse response = httpResponse.getBody();

        // We don't check the status code here since if there is an error the spring framework will throw a RestClientException
        if (response == null) {
            String errorMessage = "Request body is null";
            LOG.error(errorMessage);
            throw new RestClientException(errorMessage);
        }

        // Check ManyChat API error
        if (response.getStatus().equalsIgnoreCase("error")) {
            LOG.error(response.getMessage());
            throw new RestClientException(response.getMessage());
        }
    }
}
