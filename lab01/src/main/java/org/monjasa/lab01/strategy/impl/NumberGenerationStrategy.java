package org.monjasa.lab01.strategy.impl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.configuration2.ImmutableConfiguration;
import org.monjasa.lab01.config.CommandLineConfig;
import org.monjasa.lab01.mapper.LinearCongruentialGeneratorMapper;
import org.monjasa.lab01.model.LinearCongruentialGenerator;
import org.monjasa.lab01.service.FileService;
import org.monjasa.lab01.service.LinearCongruentialGeneratorService;
import org.monjasa.lab01.strategy.ApplicationStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.stream.Collectors;

@Singleton
public class NumberGenerationStrategy implements ApplicationStrategy {

    @Inject
    private LinearCongruentialGeneratorService generatorService;

    @Inject
    private FileService fileService;

    @Override
    public String apply(ImmutableConfiguration configuration, CommandLine commandLine) {
        LinearCongruentialGenerator generator = LinearCongruentialGeneratorMapper.fromConfiguration(configuration);

        int length = Integer.parseInt(commandLine.getOptionValue(CommandLineConfig.LENGTH_OPTION_NAME));
        long[] values = generatorService.generateLongValues(generator, length);

        String result = Arrays.stream(values)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("\n"));

        fileService.writeResultToOutputFile(result);

        return result;
    }
}
