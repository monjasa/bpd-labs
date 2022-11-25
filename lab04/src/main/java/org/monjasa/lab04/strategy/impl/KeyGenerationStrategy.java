package org.monjasa.lab04.strategy.impl;

import org.apache.commons.cli.CommandLine;
import org.monjasa.lab04.crypto.key.RsaKeyFactory;
import org.monjasa.lab04.crypto.key.RsaKeyPairGenerator;
import org.monjasa.lab04.crypto.key.pair.RsaKeyPair;
import org.monjasa.lab04.service.FileService;
import org.monjasa.lab04.strategy.ApplicationStrategy;
import org.monjasa.lab04.utils.CommandLineMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KeyGenerationStrategy implements ApplicationStrategy {

    @Inject
    private RsaKeyPairGenerator rsaKeyPairGenerator;

    @Inject
    private RsaKeyFactory rsaKeyFactory;

    @Inject
    private FileService fileService;

    @Inject
    private CommandLineMapper commandLineMapper;

    @Override
    public void apply(CommandLine commandLine) {
        RsaKeyPair keyPair = rsaKeyPairGenerator.generate();

        fileService.writeBytes("./id_rsa", rsaKeyFactory.encodeKey(keyPair.getPrivateKey()));
        fileService.writeBytes("./id_rsa.pub", rsaKeyFactory.encodeKey(keyPair.getPublicKey()));
    }
}
