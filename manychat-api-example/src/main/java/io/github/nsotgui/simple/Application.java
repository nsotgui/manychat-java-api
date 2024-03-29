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

package io.github.nsotgui.simple;

import io.github.nsotgui.manychat.*;
import io.github.nsotgui.manychat.api.ManyChatAPIClient;
import io.github.nsotgui.manychat.api.ManyChatAPIFactory;
import org.apache.commons.cli.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

/**
 * Simple example on how to use the library
 */
public class Application {
    private static String API_KEY = "";

    /**
     * Runs the example
     */
    private static void runExample() {
        try {
            // Instantiates the client
            ManyChatAPIClient manyChatAPIClient = ManyChatAPIFactory.getManyChatAPIClient(API_KEY);

            // Gets the custom fields
            List<CustomField> customFields = manyChatAPIClient.getCustomFields();
            for (CustomField field : customFields)
                System.out.println("Field: " + field);

            // Creates a tag
            Tag tagToCreate = new Tag(-1, "My new tag");
            Tag createdTag = manyChatAPIClient.createTag(tagToCreate);
            System.out.println("Created tag: " + createdTag);

            // Gets the tags
            List<Tag> tags = manyChatAPIClient.getTags();
            for (Tag tag : tags)
                System.out.println("Tag: " + tag);

            // Gets the widgets
            List<Widget> widgets = manyChatAPIClient.getWidgets();
            for (Widget widget : widgets)
                System.out.println("Widget: " + widget);

            // Creates a bot field
            BotField botFieldToCreate = new BotField("My bot name", "text", "This field store my bot name");
            BotField createdBotField = manyChatAPIClient.createBotField(botFieldToCreate);
            System.out.println("Created bot field: " + createdBotField);

            // Sets the bot field
            manyChatAPIClient.setBotField(createdBotField.getId(), "My new value");
            manyChatAPIClient.setBotField(createdBotField.getName(), "My new value by name");

            // Gets the bot fields
            List<BotField> botFields = manyChatAPIClient.getBotFields();
            for (BotField botField : botFields)
                System.out.println("BotField: " + botField);

            // Gets the subscriber info
            Subscriber subscriber = manyChatAPIClient.getSubscriberInfo("123456789");
            System.out.println("Subscriber: " + subscriber);

            // Creates a custom field
            CustomField customFieldToCreate = new CustomField();
            customFieldToCreate.setId(customFields.get(0).getId()); // This assumes that your customFields.get(0) exists
            customFieldToCreate.setValue("My value");
            manyChatAPIClient.setCustomField(subscriber.getId(), customFieldToCreate);
            System.out.println("Created custom field: " + customFieldToCreate);

        } catch (RestClientException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Main
     *
     * @param args program arguments
     */
    public static void main(String[] args) {
        Options options = createOptions();
        try {
            parseArguments(options, args);
            runExample();
        } catch (ParseException e) {
            usage(options);
        }
    }

    /**
     * Creates the options
     *
     * @return the options
     */
    private static Options createOptions() {
        Options options = new Options();
        options.addOption(Option.builder("k")
                .longOpt("api-key")
                .desc("The ManyChat API key")
                .hasArg()
                .argName("API_KEY")
                .required()
                .build());
        return options;
    }

    /**
     * Parses the program arguments
     *
     * @param options the options
     * @param args    the arguments
     * @throws ParseException
     */
    private static void parseArguments(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        API_KEY = line.getOptionValue("api-key");
    }

    /**
     * Shows the usage
     *
     * @param options the options
     */
    private static void usage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("manychat-api-example", options);
    }
}
