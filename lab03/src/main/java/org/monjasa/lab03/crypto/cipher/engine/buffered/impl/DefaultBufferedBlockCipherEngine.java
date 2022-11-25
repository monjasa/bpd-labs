package org.monjasa.lab03.crypto.cipher.engine.buffered.impl;

import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.buffered.BufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.exception.InvalidCipherTextException;
import org.monjasa.lab03.crypto.exception.OutputLengthException;

public class DefaultBufferedBlockCipherEngine implements BufferedBlockCipherEngine {
    protected byte[] buf;
    protected int bufOff;

    protected boolean forEncryption;
    protected BlockCipherEngine cipher;


    protected DefaultBufferedBlockCipherEngine() {
    }

    public DefaultBufferedBlockCipherEngine(
            BlockCipherEngine cipher) {
        this.cipher = cipher;

        buf = new byte[cipher.getBlockSize()];
        bufOff = 0;
    }

    @Override
    public BlockCipherEngine getUnderlyingCipher() {
        return cipher;
    }

    @Override
    public void initialize(
            boolean forEncryption,
            CipherParameters params)
            throws IllegalArgumentException {
        this.forEncryption = forEncryption;

        reset();

        cipher.initialize(forEncryption, params);
    }

    @Override
    public int getBlockSize() {
        return cipher.getBlockSize();
    }

    @Override
    public int getUpdateOutputSize(
            int len) {
        int total = len + bufOff;
        int leftOver = total % buf.length;

        return total - leftOver;
    }


    @Override
    public int getOutputSize(
            int length) {
        return length + bufOff;
    }

    @Override
    public int processBytes(
            byte[] in,
            int inOff,
            int len,
            byte[] out,
            int outOff)
            throws IllegalArgumentException, IllegalStateException {
        if (len < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }

        int blockSize = getBlockSize();
        int length = getUpdateOutputSize(len);

        if (length > 0) {
            if ((outOff + length) > out.length) {
                throw new OutputLengthException("output buffer too short");
            }
        }

        int resultLen = 0;
        int gapLen = buf.length - bufOff;

        if (len > gapLen) {
            System.arraycopy(in, inOff, buf, bufOff, gapLen);

            resultLen += cipher.processBlock(buf, 0, out, outOff);

            bufOff = 0;
            len -= gapLen;
            inOff += gapLen;

            while (len > buf.length) {
                resultLen += cipher.processBlock(in, inOff, out, outOff + resultLen);

                len -= blockSize;
                inOff += blockSize;
            }
        }

        System.arraycopy(in, inOff, buf, bufOff, len);

        bufOff += len;

        if (bufOff == buf.length) {
            resultLen += cipher.processBlock(buf, 0, out, outOff + resultLen);
            bufOff = 0;
        }

        return resultLen;
    }

    @Override
    public int doFinal(
            byte[] out,
            int outOff)
            throws IllegalArgumentException, IllegalStateException, InvalidCipherTextException {
        try {
            int resultLen = 0;

            if (outOff + bufOff > out.length) {
                throw new OutputLengthException("output buffer too short for doFinal()");
            }

            if (bufOff != 0) {
                throw new IllegalArgumentException("data not block size aligned");
            }

            return resultLen;
        } finally {
            reset();
        }
    }

    @Override
    public void reset() {
        for (int i = 0; i < buf.length; i++) {
            buf[i] = 0;
        }

        bufOff = 0;

        cipher.reset();
    }
}
