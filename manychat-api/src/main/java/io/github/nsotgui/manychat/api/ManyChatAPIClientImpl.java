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
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Internal implementation of the ManyChat api client
 */
final class ManyChatAPIClientImpl implements ManyChatAPIClient {
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
        ResponseEntity<CustomFieldsResponse> httpResponse = restTemplate.exchange(endpoint, HttpMethod.GET, entity, CustomFieldsResponse.class);
        CustomFieldsResponse customFieldsResponse = httpResponse.getBody();
        // TODO: better error handling
        assert customFieldsResponse != null;
        List<CustomField> customFields = customFieldsResponse.getCustomFields();
        return customFields != null ? customFields : Collections.emptyList();
    }
}
