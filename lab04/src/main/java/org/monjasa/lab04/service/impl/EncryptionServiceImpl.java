package org.monjasa.lab04.service.impl;

import lombok.SneakyThrows;
import org.monjasa.lab04.crypto.cipher.engine.AsymmetricBlockCipher;
import org.monjasa.lab04.crypto.cipher.engine.RsaCipherEngine;
import org.monjasa.lab04.crypto.cipher.parameters.impl.RSAKeyParameters;
import org.monjasa.lab04.crypto.key.RsaKeyFactory;
import org.monjasa.lab04.service.EncryptionService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Singleton
public class EncryptionServiceImpl implements EncryptionService {

    @Inject
    private RsaKeyFactory keyFactory;

    @Override
    @SneakyThrows
    public byte[] encrypt(byte[] data, byte[] encodedPublicKey) {
        RSAPublicKey publicKey = keyFactory.decodePublicKey(encodedPublicKey);
        AsymmetricBlockCipher cipherEngine = new RsaCipherEngine();
        cipherEngine.init(
                true,
                new RSAKeyParameters(false, publicKey.getModulus(), publicKey.getPublicExponent())
        );

        return cipherEngine.processBlock(data, 0, data.length);
    }

    @Override
    @SneakyThrows
    public byte[] decrypt(byte[] data, byte[] encodedPrivateKey) {
        RSAPrivateKey privateKey = keyFactory.decodePrivateKey(encodedPrivateKey);
        AsymmetricBlockCipher cipherEngine = new RsaCipherEngine();
        cipherEngine.init(
                false,
                new RSAKeyParameters(true, privateKey.getModulus(), privateKey.getPrivateExponent())
        );

        return cipherEngine.processBlock(data, 0, data.length);
    }
}
