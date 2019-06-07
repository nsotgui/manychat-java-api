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
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * The public ManyChat API Client interface
 */
public interface ManyChatAPIClient {

    /**
     * Gets the list of custom fields
     *
     * @return the custom fields
     */
    List<CustomField> getCustomFields() throws RestClientException;

    /**
     * Gets the list of tags
     *
     * @return the tags
     */
    List<Tag> getTags() throws RestClientException;
}
