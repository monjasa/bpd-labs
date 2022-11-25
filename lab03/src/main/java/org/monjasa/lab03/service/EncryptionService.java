package org.monjasa.lab03.service;

public interface EncryptionService {

    byte[] encrypt(byte[] data, String key);

}
