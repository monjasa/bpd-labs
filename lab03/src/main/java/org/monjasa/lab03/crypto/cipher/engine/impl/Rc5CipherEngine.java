package org.monjasa.lab03.crypto.cipher.engine.impl;

import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.Rc5CipherParameters;
import org.monjasa.lab03.crypto.utils.WordConverter;
import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;

public class Rc5CipherEngine implements BlockCipherEngine {

    public static final int WORD_SIZE = 64;

    public static final int WORD_SIZE_BYTES = WORD_SIZE / 8;

    /**
     * P<sub>w</sub> = Odd((e - 2) * 2 ^ WORD_SIZE),
     *
     * where e is the base of natural logarithms (e = 2.718281828...)
     */
    private static final long P64 = 0xb7e151628aed2a6bL;

    /**
     * Q<sub>w</sub> = Odd((o - 2) * 2 ^ WORD_SIZE),
     *
     * where o is the golden ratio (o = 1.61803398...)
     */
    private static final long Q64 = 0x9e3779b97f4a7c15L;

    private int numberOfRounds;

    private boolean encrypting;

    private long[] expandedKeys;

    private WordConverter wordConverter;

    @Override
    public void initialize(boolean encrypting, CipherParameters parameters) {
        if (!(parameters instanceof Rc5CipherParameters rc5CipherParameters)) {
            throw new IllegalArgumentException("Invalid parameter passed to RC5 initialization");
        }

        this.encrypting = encrypting;
        this.numberOfRounds = rc5CipherParameters.getNumberOfRounds();

        this.wordConverter = new WordConverter(WORD_SIZE, WORD_SIZE_BYTES);

        initializeExpandedKeys(rc5CipherParameters.getKey());
    }

    @Override
    public int processBlock(
            byte[] in,
            int inOff,
            byte[] out,
            int outOff) {
        return (encrypting) ? encryptBlock(in, inOff, out, outOff)
                : decryptBlock(in, inOff, out, outOff);
    }

    @Override
    public void reset() {
    }

    @Override
    public int getBlockSize() {
        return 2 * WORD_SIZE_BYTES;
    }

    private void initializeExpandedKeys(byte[] key) {
        long[] wordKeys = new long[(key.length + (WORD_SIZE_BYTES - 1)) / WORD_SIZE_BYTES];
        for (int i = 0; i != key.length; i++) {
            wordKeys[i / WORD_SIZE_BYTES] += (long) (key[i] & 0xff) << (8 * (i % WORD_SIZE_BYTES));
        }
        
        expandedKeys = new long[2 * (numberOfRounds + 1)];
        
        expandedKeys[0] = P64;
        for (int i = 1; i < expandedKeys.length; i++) {
            expandedKeys[i] = (expandedKeys[i - 1] + Q64);
        }

        int numberOfIterations = wordKeys.length > expandedKeys.length
                ? 3 * wordKeys.length
                : 3 * expandedKeys.length;

        long a = 0;
        long b = 0;

        int i = 0;
        int j = 0;

        for (int k = 0; k < numberOfIterations; k++) {
            a = expandedKeys[i] = wordConverter.rotateLeft(expandedKeys[i] + a + b, 3);
            b = wordKeys[j] = wordConverter.rotateLeft(wordKeys[j] + a + b, a + b);
            i = (i + 1) % expandedKeys.length;
            j = (j + 1) % wordKeys.length;
        }
    }
    
    private int encryptBlock(byte[] in, int inOffset, byte[] out, int outOffset) {
        long a = wordConverter.bytesToWord(in, inOffset) + expandedKeys[0];
        long b = wordConverter.bytesToWord(in, inOffset + WORD_SIZE_BYTES) + expandedKeys[1];

        for (int i = 1; i <= numberOfRounds; i++) {
            a = wordConverter.rotateLeft(a ^ b, b) + expandedKeys[2 * i];
            b = wordConverter.rotateLeft(b ^ a, a) + expandedKeys[2 * i + 1];
        }

        wordConverter.wordToBytes(a, out, outOffset);
        wordConverter.wordToBytes(b, out, outOffset + WORD_SIZE_BYTES);

        return 2 * WORD_SIZE_BYTES;
    }

    private int decryptBlock(byte[] in, int inOffset, byte[] out, int outOffset) {
        long a = wordConverter.bytesToWord(in, inOffset);
        long b = wordConverter.bytesToWord(in, inOffset + WORD_SIZE_BYTES);

        for (int i = numberOfRounds; i >= 1; i--) {
            b = wordConverter.rotateRight(b - expandedKeys[2 * i + 1], a) ^ a;
            a = wordConverter.rotateRight(a - expandedKeys[2 * i], b) ^ b;
        }

        wordConverter.wordToBytes(a - expandedKeys[0], out, outOffset);
        wordConverter.wordToBytes(b - expandedKeys[1], out, outOffset + WORD_SIZE_BYTES);

        return 2 * WORD_SIZE_BYTES;
    }
}
