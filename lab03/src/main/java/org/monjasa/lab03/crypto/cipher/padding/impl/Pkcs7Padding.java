package org.monjasa.lab03.crypto.cipher.padding.impl;

import org.monjasa.lab03.crypto.cipher.padding.BlockCipherPadding;
import org.monjasa.lab03.crypto.exception.InvalidCipherTextException;

import java.security.SecureRandom;

public class Pkcs7Padding implements BlockCipherPadding {

    @Override
    public void init(SecureRandom random) throws IllegalArgumentException {
    }


    @Override
    public int addPadding(
            byte[]  in,
            int     inOff)
    {
        byte code = (byte)(in.length - inOff);

        while (inOff < in.length)
        {
            in[inOff] = code;
            inOff++;
        }

        return code;
    }

    @Override
    public int padCount(byte[] in) throws InvalidCipherTextException {
        int count = in[in.length - 1] & 0xff;
        byte countAsbyte = (byte)count;

        // constant time version
        boolean failed = (count > in.length | count == 0);

        for (int i = 0; i < in.length; i++)
        {
            failed |= (in.length - i <= count) & (in[i] != countAsbyte);
        }

        if (failed)
        {
            throw new InvalidCipherTextException("pad block corrupted");
        }

        return count;
    }
}
