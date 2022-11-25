package org.monjasa.lab05.strategy.impl;

import org.apache.commons.cli.CommandLine;
import org.monjasa.lab05.crypto.key.DsaKeyFactory;
import org.monjasa.lab05.crypto.key.DsaKeyPairGenerator;
import org.monjasa.lab05.crypto.key.pair.DsaKeyPair;
import org.monjasa.lab05.service.FileService;
import org.monjasa.lab05.strategy.ApplicationStrategy;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class KeyGenerationStrategy implements ApplicationStrategy {

    @Inject
    private DsaKeyPairGenerator dsaKeyPairGenerator;

    @Inject
    private DsaKeyFactory rsaKeyFactory;

    @Inject
    private FileService fileService;

    @Override
    public void apply(CommandLine commandLine) {
        DsaKeyPair keyPair = dsaKeyPairGenerator.generate();

        fileService.writeBytes("./id_dsa", rsaKeyFactory.encodeKey(keyPair.getPrivateKey()));
        fileService.writeBytes("./id_dsa.pub", rsaKeyFactory.encodeKey(keyPair.getPublicKey()));
    }
}
