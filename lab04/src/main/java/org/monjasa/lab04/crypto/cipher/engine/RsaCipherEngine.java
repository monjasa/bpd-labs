package org.monjasa.lab04.crypto.cipher.engine;

import org.monjasa.lab04.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab04.crypto.cipher.parameters.impl.RSAKeyParameters;

import java.math.BigInteger;
import java.util.Arrays;

public class RsaCipherEngine implements AsymmetricBlockCipher {

    private RSAKeyParameters key;
    private boolean forEncryption;

    public void init(
            boolean forEncryption,
            CipherParameters param) {
        key = (RSAKeyParameters) param;
        this.forEncryption = forEncryption;
    }

    public int getInputBlockSize() {
        int bitSize = key.getModulus().bitLength();

        if (forEncryption) {
            return (bitSize + 7) / 8 - 1;
        } else {
            return (bitSize + 7) / 8;
        }
    }

    public int getOutputBlockSize() {
        int bitSize = key.getModulus().bitLength();

        if (forEncryption) {
            return (bitSize + 7) / 8;
        } else {
            return (bitSize + 7) / 8 - 1;
        }
    }

    public byte[] processBlock(
            byte[] in,
            int inOff,
            int inLen) {
        return convertOutput(processBlock(convertInput(in, inOff, inLen)));
    }

    public BigInteger processBlock(BigInteger input) {
        return input.modPow(key.getExponent(), key.getModulus());
    }

    private BigInteger convertInput(
            byte[] in,
            int inOff,
            int inLen) {
        if (inLen > (getInputBlockSize() + 1)) {
            throw new IllegalArgumentException("input too large for RSA cipher.");
        } else if (inLen == (getInputBlockSize() + 1) && !forEncryption) {
            throw new IllegalArgumentException("input too large for RSA cipher.");
        }

        byte[] block;

        if (inOff != 0 || inLen != in.length) {
            block = new byte[inLen];

            System.arraycopy(in, inOff, block, 0, inLen);
        } else {
            block = in;
        }

        BigInteger res = new BigInteger(1, block);
        if (res.compareTo(key.getModulus()) >= 0) {
            throw new IllegalArgumentException("input too large for RSA cipher.");
        }

        return res;
    }

    private byte[] convertOutput(
            BigInteger result) {
        byte[] output = result.toByteArray();

        if (forEncryption) {
            if (output[0] == 0 && output.length > getOutputBlockSize())        // have ended up with an extra zero byte, copy down.
            {
                byte[] tmp = new byte[output.length - 1];

                System.arraycopy(output, 1, tmp, 0, tmp.length);

                return tmp;
            }

            if (output.length < getOutputBlockSize())     // have ended up with less bytes than normal, lengthen
            {
                byte[] tmp = new byte[getOutputBlockSize()];

                System.arraycopy(output, 0, tmp, tmp.length - output.length, output.length);

                return tmp;
            }

            return output;
        } else {
            byte[] rv;
            if (output[0] == 0)        // have ended up with an extra zero byte, copy down.
            {
                rv = new byte[output.length - 1];

                System.arraycopy(output, 1, rv, 0, rv.length);
            } else        // maintain decryption time
            {
                rv = new byte[output.length];

                System.arraycopy(output, 0, rv, 0, rv.length);
            }

            Arrays.fill(output, (byte) 0);

            return rv;
        }
    }
}
