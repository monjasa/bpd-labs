package org.monjasa.lab03.crypto.cipher.engine.buffered;

import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.exception.InvalidCipherTextException;

public interface BufferedBlockCipherEngine {
    BlockCipherEngine getUnderlyingCipher();

    void initialize(
            boolean forEncryption,
            CipherParameters params)
            throws IllegalArgumentException;

    int getBlockSize();

    int getUpdateOutputSize(
            int len);

    int getOutputSize(
            int length);

    int processBytes(
            byte[] in,
            int inOff,
            int len,
            byte[] out,
            int outOff)
            throws IllegalArgumentException, IllegalStateException;

    int doFinal(
            byte[] out,
            int outOff)
            throws IllegalArgumentException, IllegalStateException, InvalidCipherTextException;

    void reset();
}
