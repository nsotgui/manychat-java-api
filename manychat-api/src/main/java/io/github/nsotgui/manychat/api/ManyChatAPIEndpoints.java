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

/**
 * ManyChat API Endpoints
 */
interface ManyChatAPIEndpoints {

    // Base URL
    String BASE_URL = "https://api.manychat.com";

    // Page endpoints
    String PAGE_CREATE_TAG = "/fb/page/createTag";
    String PAGE_GET_TAGS = "/fb/page/getTags";
    String PAGE_GET_WIDGETS = "/fb/page/getWidgets";
    String PAGE_GET_CUSTOM_FIELDS = "/fb/page/getCustomFields";
    String PAGE_GET_BOT_FIELDS = "/fb/page/getBotFields";
    String PAGE_CREATE_BOT_FIELD = "/fb/page/createBotField";
    String PAGE_SET_BOT_FIELD = "/fb/page/setBotField";
    String PAGE_SET_BOT_FIELD_BY_NAME = "/fb/page/setBotFieldByName";

    // Sending endpoints
    String SENDING_SEND_CONTENT = "/fb/sending/sendContent";
    String SENDING_SEND_CONTENT_BY_USER_REF = "/fb/sending/sendContentByUserRef";
    String SENDING_SEND_FLOW = "/fb/sending/sendFlow";

    // Subscriber endpoints
    String SUBSCRIBER_GET_INFO = "/fb/subscriber/getInfo";
    String SUBSCRIBER_FIND_BY_NAME = "/fb/subscriber/findByName";
    String SUBSCRIBER_GET_INFO_BY_USER_REF = "/fb/subscriber/getInfoByUserRef";
    String SUBSCRIBER_FIND_BY_CUSTOM_FIELD = "/fb/subscriber/findByCustomField";
    String SUBSCRIBER_ADD_TAG = "/fb/subscriber/addTag";
    String SUBSCRIBER_ADD_TAG_BY_NAME = "/fb/subscriber/addTagByName";
    String SUBSCRIBER_REMOVE_TAG = "/fb/subscriber/removeTag";
    String SUBSCRIBER_REMOVE_TAG_BY_NAME = "/fb/subscriber/removeTagByName";
    String SUBSCRIBER_SET_CUSTOM_FIELD = "/fb/subscriber/setCustomField";
    String SUBSCRIBER_SET_CUSTOM_FIELD_BY_NAME = "/fb/subscriber/setCustomFieldByName";
}
