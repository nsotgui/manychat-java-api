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

package io.github.nsotgui.spring;

import io.github.nsotgui.manychat.api.ManyChatAPIClient;
import io.github.nsotgui.manychat.api.ManyChatAPIFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ManyChatAPIClientConfig {

    @Value("${manychat.api.key}")
    private String apiKey;

    @Bean
    public ManyChatAPIClient getManyChatClient() {
        return ManyChatAPIFactory.getManyChatAPIClient(apiKey);
    }
}
