package org.monjasa.lab05.crypto.key.impl;

import lombok.SneakyThrows;
import org.monjasa.lab05.crypto.key.DsaKeyFactory;

import javax.inject.Singleton;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Singleton
public class DsaKeyFactoryImpl implements DsaKeyFactory {
    
    private final KeyFactory keyFactory;

    @SneakyThrows
    public DsaKeyFactoryImpl() {
        keyFactory = KeyFactory.getInstance("DSA");
    }

    @Override
    public byte[] encodeKey(Key key) {
        return Base64.getEncoder().encode(key.getEncoded());
    }

    @Override
    @SneakyThrows
    public DSAPublicKey decodePublicKey(byte[] encodedPublicKey) {
        byte[] decodedPublicKey = Base64.getDecoder().decode(encodedPublicKey);
        KeySpec keySpec = new X509EncodedKeySpec(decodedPublicKey);
        return (DSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    @Override
    @SneakyThrows
    public DSAPrivateKey decodePrivateKey(byte[] encodedPrivateKey) {
        byte[] decodedPrivateKey = Base64.getDecoder().decode(encodedPrivateKey);
        KeySpec keySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        return (DSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
