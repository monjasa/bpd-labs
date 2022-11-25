package org.monjasa.lab05.service;

public interface SignatureService {

    byte[] sign(byte[] data, byte[] encodedPrivateKey);

    boolean verify(byte[] data, byte[] encodedPublicKey);

}
