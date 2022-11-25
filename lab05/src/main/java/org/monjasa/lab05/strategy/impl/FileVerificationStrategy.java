package org.monjasa.lab05.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.monjasa.lab05.config.enumeration.CommandLineOption;
import org.monjasa.lab05.service.FileService;
import org.monjasa.lab05.service.SignatureService;
import org.monjasa.lab05.strategy.ApplicationStrategy;
import org.monjasa.lab05.utils.CommandLineMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Singleton
public class FileVerificationStrategy implements ApplicationStrategy {

    @Inject
    private FileService fileService;

    @Inject
    private SignatureService signatureService;

    @Inject
    private CommandLineMapper commandLineMapper;

    @Override
    public void apply(CommandLine commandLine) {
        byte[] fileBytes = fileService.readBytes(getFilePathValue(commandLine));
        byte[] keyBytes = fileService.readBytes(getKeyPathValue(commandLine));

        Instant startInstant = Instant.now();

        log.info(String.format("File verified: %b", !signatureService.verify(fileBytes, keyBytes)));

        logElapsedTime(startInstant);
    }

    private String getFilePathValue(CommandLine commandLine) {
        return commandLineMapper.toOptionValue(commandLine, CommandLineOption.FILE_PATH);
    }

    private String getKeyPathValue(CommandLine commandLine) {
        return commandLineMapper.toOptionValue(commandLine, CommandLineOption.KEY_PATH);
    }

    private void logElapsedTime(Instant startInstant) {
        long duration = Duration.between(startInstant, Instant.now()).toNanos();

        log.info(String.format("File verified in %.3f ms", (double) duration / ChronoUnit.MILLIS.getDuration().toNanos()));
    }
}
