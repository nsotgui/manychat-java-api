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
package com.nsotgui.manychat.api;

/**
 * The factory to get the ManyChat api client
 */
public final class ManyChatAPIFactory {

    /**
     * Gets the ManyChat API Client
     *
     * @param apiToken the api token
     * @return the ManyChat client
     */
    public static ManyChatAPIClient getManyChatAPIClient(String apiToken) {
        return new ManyChatAPIClientImpl(apiToken);
    }

}
