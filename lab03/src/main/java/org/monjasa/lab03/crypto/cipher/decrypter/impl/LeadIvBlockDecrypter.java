package org.monjasa.lab03.crypto.cipher.decrypter.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.monjasa.lab03.crypto.cipher.decrypter.Decrypter;
import org.monjasa.lab03.crypto.cipher.engine.BlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.buffered.BufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.CipherParametersWithIv;

import java.util.Arrays;

@RequiredArgsConstructor
public class LeadIvBlockDecrypter implements Decrypter {

    private final BlockCipherEngine ivCipherEngine;

    private final BufferedBlockCipherEngine dataCipherEngine;

    private CipherParameters parameters;

    @Override
    public void initialize(CipherParameters parameters) {
        reset();

        this.parameters = parameters;

        ivCipherEngine.initialize(false, parameters);
    }

    @Override
    public byte[] decrypt(byte[] input) {
        byte[] iv = decryptIv(Arrays.copyOf(input, ivCipherEngine.getBlockSize()));

        initializeDataCipherEngine(iv);

        return decryptData(Arrays.copyOfRange(input, iv.length, input.length));
    }

    private void initializeDataCipherEngine(byte[] iv) {
        dataCipherEngine.initialize(false, new CipherParametersWithIv(parameters, iv));
    }

    private byte[] decryptIv(byte[] ivEncrypted) {
        byte[] iv = new byte[ivCipherEngine.getBlockSize()];

        ivCipherEngine.processBlock(ivEncrypted, 0, iv, 0);

        return iv;
    }

    @SneakyThrows
    private byte[] decryptData(byte[] dataEncrypted) {
        byte[] data = new byte[dataEncrypted.length];

        int dataBytesCount = dataCipherEngine.processBytes(dataEncrypted, 0, dataEncrypted.length, data, 0);
        dataBytesCount = dataBytesCount + dataCipherEngine.doFinal(data, dataBytesCount);

        if (data.length != dataBytesCount) {
            data = Arrays.copyOf(data, dataBytesCount);
        }

        return data;
    }


    @Override
    public void reset() {
        ivCipherEngine.reset();
        dataCipherEngine.reset();

        parameters = null;
    }
}
