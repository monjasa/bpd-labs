package org.monjasa.lab01.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.monjasa.lab01.config.CommandLineConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandLineUtils {

    @SneakyThrows
    public static CommandLine parseArgsToCommandLine(String[] args) {
        Options options = new Options()
                .addOption(buildCountOption())
                .addOption(buildPeriodOption());

        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private static Option buildCountOption() {
        return Option.builder()
                .longOpt(CommandLineConfig.LENGTH_OPTION_NAME)
                .type(Integer.class)
                .hasArg()
                .build();
    }

    private static Option buildPeriodOption() {
        return Option.builder()
                .longOpt(CommandLineConfig.PERIOD_OPTION_NAME)
                .build();
    }
}
