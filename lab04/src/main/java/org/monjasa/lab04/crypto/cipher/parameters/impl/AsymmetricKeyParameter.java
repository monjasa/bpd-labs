package org.monjasa.lab04.crypto.cipher.parameters.impl;

import org.monjasa.lab04.crypto.cipher.parameters.CipherParameters;

public class AsymmetricKeyParameter
        implements CipherParameters
{
    boolean privateKey;

    public AsymmetricKeyParameter(
            boolean privateKey)
    {
        this.privateKey = privateKey;
    }

    public boolean isPrivate()
    {
        return privateKey;
    }
}
