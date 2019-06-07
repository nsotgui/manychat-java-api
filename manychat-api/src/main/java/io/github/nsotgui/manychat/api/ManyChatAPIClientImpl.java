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
import io.github.nsotgui.manychat.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Internal implementation of the ManyChat api client
 */
final class ManyChatAPIClientImpl implements ManyChatAPIClient {
    private Logger LOG = LoggerFactory.getLogger(ManyChatAPIClientImpl.class);
    private HttpEntity<String> entity;
    private RestTemplate restTemplate;

    public ManyChatAPIClientImpl(RestTemplate restTemplate, String apiToken) {
        this.restTemplate = restTemplate;
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + apiToken);
        entity = new HttpEntity<String>("parameters", headers);
    }

    public List<CustomField> getCustomFields() throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_CUSTOM_FIELDS;
        LOG.info("Retrieving Custom Fields: {}", endpoint);
        ResponseEntity<APIResponse<CustomField>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<CustomField>>() {
                });
        LOG.debug("Received response: {}", httpResponse.toString());

        processResponse(httpResponse);

        APIResponse<CustomField> apiResponse = httpResponse.getBody();
        List<CustomField> customFields = new ArrayList<>();
        if (apiResponse != null && apiResponse.getData() != null)
            customFields = apiResponse.getData();
        return customFields;
    }

    @Override
    public List<Tag> getTags() throws RestClientException {
        String endpoint = ManyChatAPIEndpoints.BASE_URL + ManyChatAPIEndpoints.PAGE_GET_TAGS;
        LOG.info("Retrieving Tags: {}", endpoint);
        ResponseEntity<APIResponse<Tag>> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
                new ParameterizedTypeReference<APIResponse<Tag>>() {
                });
        LOG.debug("Received response: {}", httpResponse.toString());

        processResponse(httpResponse);

        APIResponse<Tag> apiResponse = httpResponse.getBody();
        List<Tag> tags = new ArrayList<>();
        if (apiResponse != null && apiResponse.getData() != null)
            tags = apiResponse.getData();
        return tags;
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
