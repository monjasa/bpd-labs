package org.monjasa.lab03.crypto.cipher.decrypter;

import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;

public interface Decrypter {

    void initialize(CipherParameters parameters);

    byte[] decrypt(byte[] input);

    void reset();

}
