package org.monjasa.lab03.crypto.cipher.padding;

import org.monjasa.lab03.crypto.exception.InvalidCipherTextException;

import java.security.SecureRandom;

public interface BlockCipherPadding {

    void init(SecureRandom random) throws IllegalArgumentException;

    int addPadding(byte[] in, int inOff);

    int padCount(byte[] in) throws InvalidCipherTextException;

}
