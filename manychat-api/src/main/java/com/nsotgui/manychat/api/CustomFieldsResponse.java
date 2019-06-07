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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nsotgui.manychat.CustomField;

import java.util.ArrayList;

/**
 * The CustomField request response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class CustomFieldsResponse extends BaseResponse {
    private ArrayList<CustomField> customFields;

    public CustomFieldsResponse() {
    }

    @JsonProperty("data")
    public ArrayList<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(ArrayList<CustomField> customFields) {
        this.customFields = customFields;
    }

}
