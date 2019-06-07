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

package io.github.nsotgui;

import io.github.nsotgui.manychat.CustomField;
import io.github.nsotgui.manychat.api.ManyChatAPIClient;
import io.github.nsotgui.manychat.api.ManyChatAPIFactory;
import org.apache.commons.cli.*;

import java.util.List;

/**
 * Simple example on how to use the library
 */
public class Example {
    private static String API_KEY = "";

    /**
     * Runs the example
     */
    private static void runExample() {
        // Instantiates the client
        ManyChatAPIClient manyChatAPIClient = ManyChatAPIFactory.getManyChatAPIClient(API_KEY);

        // Gets the custom fields
        List<CustomField> customFields = manyChatAPIClient.getCustomFields();
        for (CustomField field : customFields)
            System.out.println("Field: " + field);
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
