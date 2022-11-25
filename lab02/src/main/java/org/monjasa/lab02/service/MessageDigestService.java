package org.monjasa.lab02.service;

public interface MessageDigestService {

    byte[] digest(byte[] message);

    int getDigestLength();

}
