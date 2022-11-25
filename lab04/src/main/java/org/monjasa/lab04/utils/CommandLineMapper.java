package org.monjasa.lab04.utils;

import lombok.SneakyThrows;
import org.apache.commons.cli.*;
import org.monjasa.lab04.config.enumeration.CommandLineOption;

import javax.inject.Singleton;
import java.util.Optional;

import static org.monjasa.lab04.config.enumeration.CommandLineOption.*;

@Singleton
public class CommandLineMapper {

    @SneakyThrows
    public CommandLine fromArgs(String[] args) {
        Options options = new Options()
                .addOption(buildKeyGenerationOption())
                .addOption(buildDecryptOption())
                .addOption(buildFilePathOption())
                .addOption(buildOutputFilePathOption())
                .addOption(buildKeyPathOption());

        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public String toOptionValue(CommandLine commandLine, CommandLineOption option) {
        return Optional.ofNullable(commandLine.getOptionValue(option.getName()))
                .orElseThrow(IllegalArgumentException::new);
    }

    private Option buildKeyGenerationOption() {
        return Option.builder()
                .longOpt(KEY_GENERATION.getName())
                .build();
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

    private Option buildKeyPathOption() {
        return Option.builder()
                .longOpt(KEY_PATH.getName())
                .type(String.class)
                .hasArg()
                .build();
    }
}
