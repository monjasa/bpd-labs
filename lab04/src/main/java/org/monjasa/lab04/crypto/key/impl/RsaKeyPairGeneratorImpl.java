package org.monjasa.lab04.crypto.key.impl;

import lombok.SneakyThrows;
import org.monjasa.lab04.crypto.key.RsaKeyPairGenerator;
import org.monjasa.lab04.crypto.key.pair.RsaKeyPair;

import javax.inject.Singleton;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Singleton
public class RsaKeyPairGeneratorImpl implements RsaKeyPairGenerator {

    public static final int KEY_SIZE = 1024;

    private final KeyPairGenerator keyPairGenerator;

    @SneakyThrows
    public RsaKeyPairGeneratorImpl() {
        this.keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        this.keyPairGenerator.initialize(KEY_SIZE);
    }

    @Override
    public RsaKeyPair generate() {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return RsaKeyPair.builder()
                .publicKey((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .build();
    }
}
