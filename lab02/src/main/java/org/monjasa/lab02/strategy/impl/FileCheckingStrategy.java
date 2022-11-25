package org.monjasa.lab02.strategy.impl;

import org.apache.commons.cli.CommandLine;
import org.monjasa.lab02.config.CommandLineConfig;
import org.monjasa.lab02.mapper.StringByteArrayMapper;
import org.monjasa.lab02.service.FileService;
import org.monjasa.lab02.service.MessageDigestService;
import org.monjasa.lab02.strategy.ApplicationStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;

@Singleton
public class FileCheckingStrategy implements ApplicationStrategy {

    @Inject
    private MessageDigestService messageDigestService;

    @Inject
    private FileService fileService;

    @Override
    public String apply(CommandLine commandLine) {
        byte[] fileBytes = fileService.readBytes(commandLine.getOptionValue(CommandLineConfig.FILE_OPTION_NAME));
        byte[] hashFileBytes = StringByteArrayMapper.toByteArray(fileService.readString(commandLine.getOptionValue(CommandLineConfig.HASH_FILE_OPTION_NAME)));

        return Boolean.toString(Arrays.equals(messageDigestService.digest(fileBytes), hashFileBytes));
    }
}
