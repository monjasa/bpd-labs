package org.monjasa.lab04.service;

public interface EncryptionService {

    byte[] encrypt(byte[] data, byte[] encodedPublicKey);

    byte[] decrypt(byte[] data, byte[] encodedPrivateKey);

}
