package org.monjasa.lab03;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.commons.cli.CommandLine;
import org.monjasa.lab03.config.ApplicationInjector;
import org.monjasa.lab03.config.enumeration.CommandLineOption;
import org.monjasa.lab03.strategy.ApplicationStrategy;
import org.monjasa.lab03.strategy.impl.FileDecryptionStrategy;
import org.monjasa.lab03.strategy.impl.FileEncryptionStrategy;
import org.monjasa.lab03.utils.CommandLineMapper;

public class Application {

    public static Injector injector = Guice.createInjector(ApplicationInjector.getInstance());

    public static void main(String[] args) {
        CommandLineMapper commandLineMapper = injector.getInstance(CommandLineMapper.class);
        CommandLine commandLine = commandLineMapper.fromArgs(args);
        ApplicationStrategy strategy = commandLine.hasOption(CommandLineOption.DECRYPT.getName())
                ? injector.getInstance(FileDecryptionStrategy.class)
                : injector.getInstance(FileEncryptionStrategy.class);

        strategy.apply(commandLine);
    }
}
