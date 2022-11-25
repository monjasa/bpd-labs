package org.monjasa.lab03.crypto.cipher.engine;


import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;

public interface BlockCipherEngine {

    void initialize(boolean encrypting, CipherParameters parameters) throws IllegalArgumentException;

    int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws IllegalArgumentException, IllegalStateException;

    void reset();

    int getBlockSize();

}