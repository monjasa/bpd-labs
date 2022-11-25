package org.monjasa.lab03.crypto.cipher.encrypter;

import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;

public interface Encrypter {

    void initialize(CipherParameters parameters);

    byte[] encrypt(byte[] input);

    void reset();

    int getIvBlockSize();

    int getDataBlockSize();

}
