package org.monjasa.lab03.crypto.cipher.parameters.impl;

import lombok.Getter;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;

@Getter
public class Rc5CipherParameters implements CipherParameters {

    private static final int MAX_KEY_LENGTH = 255;

    private final byte[] key;

    private final int numberOfRounds;

    public Rc5CipherParameters(byte[]  key, int numberOfRounds) {
        if (key.length > MAX_KEY_LENGTH) {
            throw new IllegalArgumentException("RC5 key length cannot be greater than 255 bytes");
        }

        this.key = key.clone();
        this.numberOfRounds = numberOfRounds;
    }
}
