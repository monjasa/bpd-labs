package org.monjasa.lab03.crypto.cipher.parameters.impl;

import lombok.Getter;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;

@Getter
public class CipherParametersWithIv implements CipherParameters {

    private final byte[] iv;

    private final CipherParameters parameters;

    public CipherParametersWithIv(CipherParameters parameters, byte[] iv) {
        this(parameters, iv, 0, iv.length);
    }

    public CipherParametersWithIv(CipherParameters parameters, byte[] iv, int ivOff, int ivLen) {
        this.iv = new byte[ivLen];
        this.parameters = parameters;

        System.arraycopy(iv, ivOff, this.iv, 0, ivLen);
    }
}
