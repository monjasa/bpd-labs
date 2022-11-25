package org.monjasa.lab05.service.impl;

import lombok.SneakyThrows;
import org.monjasa.lab05.crypto.key.DsaKeyFactory;
import org.monjasa.lab05.service.SignatureService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

@Singleton
public class SignatureServiceImpl implements SignatureService {

    @Inject
    private DsaKeyFactory keyFactory;

    @Override
    @SneakyThrows
    public byte[] sign(byte[] data, byte[] encodedPrivateKey) {
        DSAPrivateKey privateKey = keyFactory.decodePrivateKey(encodedPrivateKey);

        Signature signature = Signature.getInstance("SHA256withDSA");
        signature.initSign(privateKey);
        signature.update(data);

        return signature.sign();
    }

    @Override
    @SneakyThrows
    public boolean verify(byte[] data, byte[] encodedPublicKey) {
        DSAPublicKey publicKey = keyFactory.decodePublicKey(encodedPublicKey);

        Signature signature = Signature.getInstance("SHA256withDSA");
        signature.initVerify(publicKey);
        signature.update(data);

        return signature.verify(data);
    }
}
