package org.monjasa.lab01.strategy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.configuration2.ImmutableConfiguration;

public interface ApplicationStrategy {

    String apply(ImmutableConfiguration configuration, CommandLine commandLine);

}
