package org.monjasa.lab04.crypto.key.impl;

import lombok.SneakyThrows;
import org.monjasa.lab04.crypto.key.RsaKeyFactory;

import javax.inject.Singleton;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Singleton
public class RsaKeyFactoryImpl implements RsaKeyFactory {
    
    private final KeyFactory rsaKeyFactory;

    @SneakyThrows
    public RsaKeyFactoryImpl() {
        this.rsaKeyFactory = KeyFactory.getInstance("RSA");
    }

    @Override
    public byte[] encodeKey(Key key) {
        return Base64.getEncoder().encode(key.getEncoded());
    }

    @Override
    @SneakyThrows
    public RSAPublicKey decodePublicKey(byte[] encodedPublicKey) {
        byte[] decodedPublicKey = Base64.getDecoder().decode(encodedPublicKey);
        KeySpec keySpec = new X509EncodedKeySpec(decodedPublicKey);
        return (RSAPublicKey) rsaKeyFactory.generatePublic(keySpec);
    }

    @Override
    @SneakyThrows
    public RSAPrivateKey decodePrivateKey(byte[] encodedPrivateKey) {
        byte[] decodedPrivateKey = Base64.getDecoder().decode(encodedPrivateKey);
        KeySpec keySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        return (RSAPrivateKey) rsaKeyFactory.generatePrivate(keySpec);
    }
}
