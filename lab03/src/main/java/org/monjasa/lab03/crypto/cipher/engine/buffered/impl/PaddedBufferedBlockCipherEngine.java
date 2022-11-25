package org.monjasa.lab03.crypto.cipher.engine.buffered.impl;

import org.monjasa.lab03.crypto.cipher.padding.BlockCipherPadding;
import org.monjasa.lab03.crypto.cipher.padding.impl.Pkcs7Padding;
import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.exception.InvalidCipherTextException;
import org.monjasa.lab03.crypto.exception.OutputLengthException;

public class PaddedBufferedBlockCipherEngine
        extends DefaultBufferedBlockCipherEngine
{
    protected BlockCipherPadding padding;

    public PaddedBufferedBlockCipherEngine(
            BlockCipherEngine cipher,
            BlockCipherPadding  padding)
    {
        this.cipher = cipher;
        this.padding = padding;

        buf = new byte[cipher.getBlockSize()];
        bufOff = 0;
    }

    public PaddedBufferedBlockCipherEngine(
            BlockCipherEngine cipher)
    {
        this(cipher, new Pkcs7Padding());
    }

    public void initialize(
            boolean             forEncryption,
            CipherParameters params)
            throws IllegalArgumentException
    {
        this.forEncryption = forEncryption;

        reset();

        padding.init(null);

        cipher.initialize(forEncryption, params);
    }

    public int getOutputSize(
            int len)
    {
        int total       = len + bufOff;
        int leftOver    = total % buf.length;

        if (leftOver == 0)
        {
            if (forEncryption)
            {
                return total + buf.length;
            }

            return total;
        }

        return total - leftOver + buf.length;
    }

    public int getUpdateOutputSize(
            int len)
    {
        int total       = len + bufOff;
        int leftOver    = total % buf.length;

        if (leftOver == 0)
        {
            return Math.max(0, total - buf.length);
        }

        return total - leftOver;
    }

    public int processBytes(
            byte[]      in,
            int         inOff,
            int         len,
            byte[]      out,
            int         outOff)
            throws IllegalArgumentException, IllegalStateException
    {
        if (len < 0)
        {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }

        int blockSize   = getBlockSize();
        int length      = getUpdateOutputSize(len);

        if (length > 0)
        {
            if ((outOff + length) > out.length)
            {
                throw new OutputLengthException("output buffer too short");
            }
        }

        int resultLen = 0;
        int gapLen = buf.length - bufOff;

        if (len > gapLen)
        {
            System.arraycopy(in, inOff, buf, bufOff, gapLen);

            resultLen += cipher.processBlock(buf, 0, out, outOff);

            bufOff = 0;
            len -= gapLen;
            inOff += gapLen;

            while (len > buf.length)
            {
                resultLen += cipher.processBlock(in, inOff, out, outOff + resultLen);

                len -= blockSize;
                inOff += blockSize;
            }
        }

        System.arraycopy(in, inOff, buf, bufOff, len);

        bufOff += len;

        return resultLen;
    }

    public int doFinal(
            byte[]  out,
            int     outOff)
            throws IllegalArgumentException, IllegalStateException, InvalidCipherTextException
    {
        int blockSize = cipher.getBlockSize();
        int resultLen = 0;

        if (forEncryption)
        {
            if (bufOff == blockSize)
            {
                if ((outOff + 2 * blockSize) > out.length)
                {
                    reset();

                    throw new OutputLengthException("output buffer too short");
                }

                resultLen = cipher.processBlock(buf, 0, out, outOff);
                bufOff = 0;
            }

            padding.addPadding(buf, bufOff);

            resultLen += cipher.processBlock(buf, 0, out, outOff + resultLen);

            reset();
        }
        else
        {
            if (bufOff == blockSize)
            {
                resultLen = cipher.processBlock(buf, 0, buf, 0);
                bufOff = 0;
            }
            else
            {
                reset();

                throw new IllegalArgumentException("last block incomplete in decryption");
            }

            try
            {
                resultLen -= padding.padCount(buf);

                System.arraycopy(buf, 0, out, outOff, resultLen);
            }
            finally
            {
                reset();
            }
        }

        return resultLen;
    }
}