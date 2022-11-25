package org.monjasa.lab04;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.CommandLine;
import org.monjasa.lab04.config.ApplicationInjector;
import org.monjasa.lab04.config.enumeration.CommandLineOption;
import org.monjasa.lab04.strategy.impl.FileDecryptionStrategy;
import org.monjasa.lab04.strategy.impl.FileEncryptionStrategy;
import org.monjasa.lab04.strategy.impl.KeyGenerationStrategy;
import org.monjasa.lab04.utils.CommandLineMapper;

public class Application {

    public static Injector injector = Guice.createInjector(ApplicationInjector.getInstance());

    public static void main(String[] args) {
        CommandLineMapper commandLineMapper = injector.getInstance(CommandLineMapper.class);
        CommandLine commandLine = commandLineMapper.fromArgs(args);

        if (commandLine.hasOption(CommandLineOption.KEY_GENERATION.getName())) {
            injector.getInstance(KeyGenerationStrategy.class).apply(commandLine);
        } else if (commandLine.hasOption(CommandLineOption.DECRYPT.getName())) {
            injector.getInstance(FileDecryptionStrategy.class).apply(commandLine);
        } else {
            injector.getInstance(FileEncryptionStrategy.class).apply(commandLine);
        }
    }
}
