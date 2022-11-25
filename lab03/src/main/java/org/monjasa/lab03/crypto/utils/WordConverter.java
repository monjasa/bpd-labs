package org.monjasa.lab03.crypto.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WordConverter {

    private final int wordSize;

    private final int bytesPerWord;

    public long rotateLeft(long word, long rotateNumber) {
        return ((word << (rotateNumber & (wordSize - 1))) | (word >>> (wordSize - (rotateNumber & (wordSize - 1)))));
    }

    public long rotateRight(long word, long rotateNumber) {
        return ((word >>> (rotateNumber & (wordSize - 1))) | (word << (wordSize - (rotateNumber & (wordSize - 1)))));
    }

    public long bytesToWord(byte[] in, int inOffset) {
        long word = 0;

        for (int i = bytesPerWord - 1; i >= 0; i--) {
            word = (word << 8) + (in[i + inOffset] & 0xff);
        }

        return word;
    }

    public void wordToBytes(long word, byte[] out, int outOffset) {
        for (int i = 0; i < bytesPerWord; i++) {
            out[i + outOffset] = (byte) word;
            word >>>= 8;
        }
    }
}
