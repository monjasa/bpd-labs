package org.monjasa.lab03.crypto.cipher.encrypter.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.monjasa.lab03.crypto.cipher.encrypter.Encrypter;
import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.buffered.BufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.CipherParametersWithIv;

import java.nio.ByteBuffer;

@RequiredArgsConstructor
public class LeadIvBlockEncrypter implements Encrypter {

    private final BlockCipherEngine ivCipherEngine;

    private final BufferedBlockCipherEngine dataCipherEngine;

    private CipherParametersWithIv parameters;

    @Override
    public void initialize(CipherParameters parameters) {
        if (!(parameters instanceof CipherParametersWithIv)) {
            throw new IllegalArgumentException();
        }

        reset();

        this.parameters = (CipherParametersWithIv) parameters;

        ivCipherEngine.initialize(true, this.parameters.getParameters());
        dataCipherEngine.initialize(true, this.parameters);
    }

    @Override
    @SneakyThrows
    public byte[] encrypt(byte[] input) {
        byte[] ivEncrypted = encryptIv(parameters.getIv());
        byte[] dataEncrypted = encryptData(input);

        return ByteBuffer.allocate(ivEncrypted.length + dataEncrypted.length)
                .put(ivEncrypted)
                .put(dataEncrypted)
                .array();
    }

    private byte[] encryptIv(byte[] iv) {
        byte[] ivEncrypted = new byte[ivCipherEngine.getBlockSize()];

        ivCipherEngine.processBlock(iv, 0, ivEncrypted, 0);

        return ivEncrypted;
    }

    @SneakyThrows
    private byte[] encryptData(byte[] data) {
        byte[] dataEncrypted = new byte[dataCipherEngine.getOutputSize(data.length)];

        int dataEncryptedBytesCount = dataCipherEngine.processBytes(data, 0, data.length, dataEncrypted, 0);
        dataCipherEngine.doFinal(dataEncrypted, dataEncryptedBytesCount);

        return dataEncrypted;
    }

    @Override
    public void reset() {
        ivCipherEngine.reset();
        dataCipherEngine.reset();

        parameters = null;
    }

    @Override
    public int getIvBlockSize() {
        return ivCipherEngine.getBlockSize();
    }

    @Override
    public int getDataBlockSize() {
        return dataCipherEngine.getBlockSize();
    }
}
