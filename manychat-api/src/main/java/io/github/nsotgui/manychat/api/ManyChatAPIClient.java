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

import io.github.nsotgui.manychat.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * The public ManyChat API Client interface
 */
public interface ManyChatAPIClient {

    /**
     * Creates a tag
     *
     * @param tag the tag to create
     * @return the tag created
     * @throws RestClientException
     */
    Tag createTag(Tag tag) throws RestClientException;

    /**
     * Gets the list of tags
     *
     * @return the tags
     */
    List<Tag> getTags() throws RestClientException;

    /**
     * Gets the list of widgets
     *
     * @return the widgets
     */
    List<Widget> getWidgets() throws RestClientException;

    /**
     * Gets the list of custom fields
     *
     * @return the custom fields
     */
    List<CustomField> getCustomFields() throws RestClientException;

    /**
     * Gets the list of bot fields
     *
     * @return the bot fields
     */
    List<BotField> getBotFields() throws RestClientException;

    /**
     * Creates a bot field
     *
     * @param botField the bot field to create
     * @return the bot field created
     * @throws RestClientException
     */
    BotField createBotField(BotField botField) throws RestClientException;

    /**
     * Sets the bot field to the specified value
     *
     * @param botFieldId the bot field id
     * @param value      the value
     * @throws RestClientException
     */
    void setBotField(Integer botFieldId, String value) throws RestClientException;

    /**
     * Sets the bot field by name to the specified value
     *
     * @param botFieldName the bot field name
     * @param value        the value
     * @throws RestClientException
     */
    void setBotField(String botFieldName, String value) throws RestClientException;

    /**
     * Gets subscriber info
     *
     * @param subscriberId the subscriber id
     * @return the subcriber info
     * @throws RestClientException
     */
    Subscriber getSubscriberInfo(String subscriberId) throws RestClientException;

}
