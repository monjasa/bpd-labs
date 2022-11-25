package org.monjasa.lab05;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.CommandLine;
import org.monjasa.lab05.config.ApplicationInjector;
import org.monjasa.lab05.config.enumeration.CommandLineOption;
import org.monjasa.lab05.strategy.impl.FileSigningStrategy;
import org.monjasa.lab05.strategy.impl.FileVerificationStrategy;
import org.monjasa.lab05.strategy.impl.KeyGenerationStrategy;
import org.monjasa.lab05.utils.CommandLineMapper;

public class Application {

    public static Injector injector = Guice.createInjector(ApplicationInjector.getInstance());

    public static void main(String[] args) {
        CommandLineMapper commandLineMapper = injector.getInstance(CommandLineMapper.class);
        CommandLine commandLine = commandLineMapper.fromArgs(args);

        if (commandLine.hasOption(CommandLineOption.KEY_GENERATION.getName())) {
            injector.getInstance(KeyGenerationStrategy.class).apply(commandLine);
        } else if (commandLine.hasOption(CommandLineOption.SIGN.getName())) {
            injector.getInstance(FileSigningStrategy.class).apply(commandLine);
        } else if (commandLine.hasOption(CommandLineOption.VERIFY.getName())) {
            injector.getInstance(FileVerificationStrategy.class).apply(commandLine);
        }
    }
}
