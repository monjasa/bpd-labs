package org.monjasa.lab03.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.monjasa.lab03.config.enumeration.CommandLineOption;
import org.monjasa.lab03.service.EncryptionService;
import org.monjasa.lab03.service.FileService;
import org.monjasa.lab03.strategy.ApplicationStrategy;
import org.monjasa.lab03.utils.CommandLineMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Singleton
public class FileEncryptionStrategy implements ApplicationStrategy {

    @Inject
    private FileService fileService;

    @Inject
    private EncryptionService encryptionService;

    @Inject
    private CommandLineMapper commandLineMapper;

    @Override
    public void apply(CommandLine commandLine) {
        byte[] fileBytes = fileService.readBytes(getFilePathValue(commandLine));

        Instant startInstant = Instant.now();

        byte[] encryptedFileBytes = encryptionService.encrypt(fileBytes, getKeyValue(commandLine));

        logElapsedTime(startInstant);

        fileService.writeBytes(getOutputPathValue(commandLine), encryptedFileBytes);
    }

    private String getFilePathValue(CommandLine commandLine) {
        return commandLineMapper.toOptionValue(commandLine, CommandLineOption.FILE_PATH);
    }

    private String getOutputPathValue(CommandLine commandLine) {
        return commandLineMapper.toOptionValue(commandLine, CommandLineOption.OUTPUT_FILE_PATH);
    }

    private String getKeyValue(CommandLine commandLine) {
        return commandLineMapper.toOptionValue(commandLine, CommandLineOption.KEY);
    }

    private void logElapsedTime(Instant startInstant) {
        long duration = Duration.between(startInstant, Instant.now()).toNanos();

        log.info(String.format("File encrypted in %.3f ms", (double) duration / ChronoUnit.MILLIS.getDuration().toNanos()));
    }
}
