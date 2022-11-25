package org.monjasa.lab05.strategy;

import org.apache.commons.cli.CommandLine;

public interface ApplicationStrategy {

    void apply(CommandLine commandLine);

}
