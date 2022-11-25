package org.monjasa.lab05.crypto.key.impl;

import lombok.SneakyThrows;
import org.monjasa.lab05.crypto.key.DsaKeyPairGenerator;
import org.monjasa.lab05.crypto.key.pair.DsaKeyPair;

import javax.inject.Singleton;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

@Singleton
public class DsaKeyPairGeneratorImpl implements DsaKeyPairGenerator {

    private final KeyPairGenerator keyPairGenerator;

    @SneakyThrows
    public DsaKeyPairGeneratorImpl() {
        keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(2048);
    }

    @Override
    public DsaKeyPair generate() {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return DsaKeyPair.builder()
                .publicKey((DSAPublicKey) keyPair.getPublic())
                .privateKey((DSAPrivateKey) keyPair.getPrivate())
                .build();
    }
}
