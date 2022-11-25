package org.monjasa.lab04.crypto.cipher.engine;

import org.monjasa.lab04.crypto.cipher.exception.InvalidCipherTextException;
import org.monjasa.lab04.crypto.cipher.parameters.CipherParameters;

public interface AsymmetricBlockCipher {

    void init(boolean forEncryption, CipherParameters param);

    int getInputBlockSize();

    int getOutputBlockSize();

    byte[] processBlock(byte[] in, int inOff, int len)
            throws InvalidCipherTextException;
}
