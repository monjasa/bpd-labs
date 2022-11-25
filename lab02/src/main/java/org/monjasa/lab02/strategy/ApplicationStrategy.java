package org.monjasa.lab02.strategy;

import org.apache.commons.cli.CommandLine;

public interface ApplicationStrategy {

    String apply(CommandLine commandLine);

}
