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

package io.github.nsotgui.manychat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a subscriber
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscriber {
    private String id;
    private String pageId;
    private String status;
    private String firstName;
    private String lastName;
    private String name;
    private String gender;
    private String profilePic;
    private String locale;
    private String language;
    private String timezone;
    private String liveChatUrl;
    private String lastInputText;
    private String dateSubscribed;
    private String dateLastInteraction;
    private String dateLastSeen;
    private Boolean isFollowupEnabled;
    private List<CustomField> customFields;
    private List<Tag> tags;

    public Subscriber() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("page_id")
    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("profile_pic")
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("live_chat_url")
    public String getLiveChatUrl() {
        return liveChatUrl;
    }

    public void setLiveChatUrl(String liveChatUrl) {
        this.liveChatUrl = liveChatUrl;
    }

    @JsonProperty("last_input_text")
    public String getLastInputText() {
        return lastInputText;
    }

    public void setLastInputText(String lastInputText) {
        this.lastInputText = lastInputText;
    }

    @JsonProperty("subscribed")
    public String getDateSubscribed() {
        return dateSubscribed;
    }

    public void setDateSubscribed(String dateSubscribed) {
        this.dateSubscribed = dateSubscribed;
    }

    @JsonProperty("last_interaction")
    public String getDateLastInteraction() {
        return dateLastInteraction;
    }

    public void setDateLastInteraction(String dateLastInteraction) {
        this.dateLastInteraction = dateLastInteraction;
    }

    @JsonProperty("last_seen")
    public String getDateLastSeen() {
        return dateLastSeen;
    }

    public void setDateLastSeen(String dateLastSeen) {
        this.dateLastSeen = dateLastSeen;
    }

    @JsonProperty("is_followup_enabled")
    public Boolean getFollowupEnabled() {
        return isFollowupEnabled;
    }

    public void setFollowupEnabled(Boolean followupEnabled) {
        isFollowupEnabled = followupEnabled;
    }

    @JsonProperty("custom_fields")
    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id='" + id + '\'' +
                ", pageId='" + pageId + '\'' +
                ", status='" + status + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
