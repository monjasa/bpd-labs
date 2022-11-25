package org.monjasa.lab01.strategy.impl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.configuration2.ImmutableConfiguration;
import org.monjasa.lab01.mapper.LinearCongruentialGeneratorMapper;
import org.monjasa.lab01.model.LinearCongruentialGenerator;
import org.monjasa.lab01.service.LinearCongruentialGeneratorService;
import org.monjasa.lab01.strategy.ApplicationStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PeriodDeterminationStrategy implements ApplicationStrategy {

    @Inject
    private LinearCongruentialGeneratorService generatorService;

    @Override
    public String apply(ImmutableConfiguration configuration, CommandLine commandLine) {
        LinearCongruentialGenerator generator = LinearCongruentialGeneratorMapper.fromConfiguration(configuration);

        long periodLength = generatorService.determinePeriodLength(generator);

        return String.valueOf(periodLength);
    }
}
