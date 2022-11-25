package org.monjasa.lab02.strategy.impl;

import org.apache.commons.cli.CommandLine;
import org.monjasa.lab02.config.CommandLineConfig;
import org.monjasa.lab02.mapper.StringByteArrayMapper;
import org.monjasa.lab02.service.FileService;
import org.monjasa.lab02.service.MessageDigestService;
import org.monjasa.lab02.strategy.ApplicationStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HashingStrategy implements ApplicationStrategy {

    @Inject
    private MessageDigestService messageDigestService;

    @Inject
    private FileService fileService;

    @Override
    public String apply(CommandLine commandLine) {
        byte[] bytes = commandLine.hasOption(CommandLineConfig.FILE_OPTION_NAME)
                ? digestFile(commandLine.getOptionValue(CommandLineConfig.FILE_OPTION_NAME))
                : digestMessage(commandLine.getOptionValue(CommandLineConfig.MESSAGE_OPTION_NAME));

        String messageDigest = StringByteArrayMapper.toHexString(messageDigestService.digest(bytes));
        fileService.writeResult(messageDigest);
        return messageDigest;
    }

    private byte[] digestMessage(String message) {
        return message.getBytes();

    }

    private byte[] digestFile(String filePath) {
        return fileService.readBytes(filePath);
    }
}
