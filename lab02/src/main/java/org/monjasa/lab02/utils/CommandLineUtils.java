package org.monjasa.lab02.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.cli.*;
import org.monjasa.lab02.config.CommandLineConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandLineUtils {

    @SneakyThrows
    public static CommandLine parseArgsToCommandLine(String[] args) {
        Options options = new Options()
                .addOption(buildMessageOption())
                .addOption(buildCheckOption())
                .addOption(buildFileOption())
                .addOption(buildHashFileOption());

        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private static Option buildMessageOption() {
        return Option.builder()
                .longOpt(CommandLineConfig.MESSAGE_OPTION_NAME)
                .type(String.class)
                .hasArg()
                .build();
    }

    private static Option buildCheckOption() {
        return Option.builder()
                .longOpt(CommandLineConfig.CHECK_OPTION_NAME)
                .build();
    }

    private static Option buildFileOption() {
        return Option.builder()
                .longOpt(CommandLineConfig.FILE_OPTION_NAME)
                .type(String.class)
                .hasArg()
                .build();
    }

    private static Option buildHashFileOption() {
        return Option.builder()
                .longOpt(CommandLineConfig.HASH_FILE_OPTION_NAME)
                .type(String.class)
                .hasArg()
                .build();
    }
}
