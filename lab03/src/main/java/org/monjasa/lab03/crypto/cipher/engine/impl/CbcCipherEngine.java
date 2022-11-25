package org.monjasa.lab03.crypto.cipher.engine.impl;

import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.CipherParametersWithIv;

import java.util.Arrays;

public class CbcCipherEngine implements BlockCipherEngine {

    private final byte[] iv;
    private byte[] cbcV;
    private byte[] cbcNextV;

    private final int blockSize;
    private final BlockCipherEngine cipher;
    private boolean encrypting;

    public CbcCipherEngine(BlockCipherEngine cipher) {
        this.cipher = cipher;
        this.blockSize = cipher.getBlockSize();

        this.iv = new byte[blockSize];
        this.cbcV = new byte[blockSize];
        this.cbcNextV = new byte[blockSize];
    }

    public void initialize(boolean encrypting, CipherParameters parameters) throws IllegalArgumentException {
        boolean oldEncrypting = this.encrypting;

        this.encrypting = encrypting;

        if (parameters instanceof CipherParametersWithIv ivParam) {
            byte[] iv = ivParam.getIv();

            if (iv.length != blockSize) {
                throw new IllegalArgumentException("Initialization vector must be the same length as block size");
            }

            System.arraycopy(iv, 0, this.iv, 0, iv.length);

            reset();

            if (ivParam.getParameters() != null) {
                cipher.initialize(encrypting, ivParam.getParameters());
            } else if (oldEncrypting != encrypting) {
                throw new IllegalArgumentException("Cannot change encrypting state without providing key");
            }
        } else {
            reset();

            if (parameters != null) {
                cipher.initialize(encrypting, parameters);
            } else if (oldEncrypting != encrypting) {
                throw new IllegalArgumentException("Cannot change encrypting state without providing key");
            }
        }
    }

    public int getBlockSize() {
        return cipher.getBlockSize();
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff)
            throws IllegalArgumentException, IllegalStateException {
        return (encrypting) ? encryptBlock(in, inOff, out, outOff) : decryptBlock(in, inOff, out, outOff);
    }

    public void reset() {
        System.arraycopy(iv, 0, cbcV, 0, iv.length);
        Arrays.fill(cbcNextV, (byte) 0);

        cipher.reset();
    }

    private int encryptBlock(byte[] in, int inOff, byte[] out, int outOff)
            throws IllegalArgumentException, IllegalStateException {
        if ((inOff + blockSize) > in.length) {
            throw new IllegalArgumentException("input buffer too short");
        }

        for (int i = 0; i < blockSize; i++) {
            cbcV[i] ^= in[inOff + i];
        }

        int length = cipher.processBlock(cbcV, 0, out, outOff);

        System.arraycopy(out, outOff, cbcV, 0, cbcV.length);

        return length;
    }

    private int decryptBlock(byte[] in, int inOff, byte[] out, int outOff)
            throws IllegalArgumentException, IllegalStateException {
        if ((inOff + blockSize) > in.length) {
            throw new IllegalArgumentException("Input buffer is too short");
        }

        System.arraycopy(in, inOff, cbcNextV, 0, blockSize);

        int length = cipher.processBlock(in, inOff, out, outOff);

        for (int i = 0; i < blockSize; i++) {
            out[outOff + i] ^= cbcV[i];
        }

        byte[] tmp;

        tmp = cbcV;
        cbcV = cbcNextV;
        cbcNextV = tmp;

        return length;
    }
}
