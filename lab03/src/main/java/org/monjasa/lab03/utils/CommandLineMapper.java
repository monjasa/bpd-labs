package org.monjasa.lab03.utils;

import lombok.SneakyThrows;
import org.apache.commons.cli.*;
import org.monjasa.lab03.config.enumeration.CommandLineOption;

import javax.inject.Singleton;
import java.util.Optional;

import static org.monjasa.lab03.config.enumeration.CommandLineOption.*;

@Singleton
public class CommandLineMapper {

    @SneakyThrows
    public CommandLine fromArgs(String[] args) {
        Options options = new Options()
                .addOption(buildDecryptOption())
                .addOption(buildFilePathOption())
                .addOption(buildOutputFilePathOption())
                .addOption(buildKeyOption());

        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public String toOptionValue(CommandLine commandLine, CommandLineOption option) {
        return Optional.ofNullable(commandLine.getOptionValue(option.getName()))
                .orElseThrow(IllegalArgumentException::new);
    }

    private Option buildDecryptOption() {
        return Option.builder()
                .longOpt(DECRYPT.getName())
                .build();
    }

    private Option buildFilePathOption() {
        return Option.builder()
                .longOpt(FILE_PATH.getName())
                .type(String.class)
                .hasArg()
                .build();
    }

    private Option buildOutputFilePathOption() {
        return Option.builder()
                .longOpt(OUTPUT_FILE_PATH.getName())
                .type(String.class)
                .hasArg()
                .build();
    }

    private Option buildKeyOption() {
        return Option.builder()
                .longOpt(KEY.getName())
                .type(String.class)
                .hasArg()
                .build();
    }
}
