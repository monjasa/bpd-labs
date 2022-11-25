package org.monjasa.lab01;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.ImmutableConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.monjasa.lab01.config.ApplicationInjector;
import org.monjasa.lab01.config.CommandLineConfig;
import org.monjasa.lab01.strategy.ApplicationStrategy;
import org.monjasa.lab01.strategy.impl.NumberGenerationStrategy;
import org.monjasa.lab01.strategy.impl.PeriodDeterminationStrategy;
import org.monjasa.lab01.utils.CommandLineUtils;

public class Application {

    public static final String APPLICATION_PROPERTIES_FILE_NAME = "application.properties";

    public static Injector injector = Guice.createInjector(ApplicationInjector.getInstance());

    public static void main(String[] args) {
        ImmutableConfiguration configuration = getFileBasedConfiguration();
        CommandLine commandLine = CommandLineUtils.parseArgsToCommandLine(args);

        ApplicationStrategy strategy = commandLine.hasOption(CommandLineConfig.PERIOD_OPTION_NAME)
                ? injector.getInstance(PeriodDeterminationStrategy.class)
                : injector.getInstance(NumberGenerationStrategy.class);

        String result = strategy.apply(configuration, commandLine);
        System.out.println(result);
    }

    @SneakyThrows
    private static ImmutableConfiguration getFileBasedConfiguration() {
        Parameters parameters = new Parameters();
        return new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                .configure(parameters.properties().setFileName(APPLICATION_PROPERTIES_FILE_NAME))
                .getConfiguration();
    }
}
