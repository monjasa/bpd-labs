package org.monjasa.lab02;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.monjasa.lab02.config.ApplicationInjector;
import org.monjasa.lab02.config.CommandLineConfig;
import org.monjasa.lab02.strategy.ApplicationStrategy;
import org.monjasa.lab02.strategy.impl.FileCheckingStrategy;
import org.monjasa.lab02.strategy.impl.HashingStrategy;
import org.monjasa.lab02.utils.CommandLineUtils;

@Slf4j
public class Application {

    public static Injector injector = Guice.createInjector(ApplicationInjector.getInstance());

    public static void main(String[] args) {
        CommandLine commandLine = CommandLineUtils.parseArgsToCommandLine(args);
        ApplicationStrategy strategy = commandLine.hasOption(CommandLineConfig.CHECK_OPTION_NAME)
                ? injector.getInstance(FileCheckingStrategy.class)
                : injector.getInstance(HashingStrategy.class);

        log.info(strategy.apply(commandLine));
    }
}
