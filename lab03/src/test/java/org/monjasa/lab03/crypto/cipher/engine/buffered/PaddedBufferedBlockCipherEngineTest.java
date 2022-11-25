package org.monjasa.lab03.crypto.cipher.engine.buffered;

import org.junit.jupiter.api.Test;
import org.monjasa.lab03.crypto.cipher.engine.buffered.BufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.buffered.impl.PaddedBufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.CbcCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.Rc5CipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.CipherParametersWithIv;
import org.monjasa.lab03.crypto.cipher.parameters.impl.Rc5CipherParameters;
import org.monjasa.lab03.crypto.encoder.base.Hex;
import org.monjasa.lab03.crypto.exception.InvalidCipherTextException;

import static org.assertj.core.api.Assertions.assertThat;

class PaddedBufferedBlockCipherEngineTest {

    @Test
    void testCipherEngine() throws InvalidCipherTextException {
        byte[] data = Hex.decode("00000000000000000000000000000000");

        CipherParameters parameters = new CipherParametersWithIv(
                new Rc5CipherParameters(Hex.decode("00"), 12),
                Hex.decode("00000000000000000000000000000000")
        );

        BufferedBlockCipherEngine cipherEngine = new PaddedBufferedBlockCipherEngine(new CbcCipherEngine(new Rc5CipherEngine()));

        cipherEngine.initialize(true, parameters);
        byte[] encryptedData = new byte[cipherEngine.getOutputSize(data.length)];
        int encryptedDataBytesCount = cipherEngine.processBytes(data, 0, data.length, encryptedData, 0);
        cipherEngine.doFinal(encryptedData, encryptedDataBytesCount);

        cipherEngine.initialize(false, parameters);
        byte[] decryptedData = new byte[data.length];
        int decryptedDataBytesCount = cipherEngine.processBytes(encryptedData, 0, encryptedData.length, decryptedData, 0);
        cipherEngine.doFinal(decryptedData, decryptedDataBytesCount);

        assertThat(data).isEqualTo(decryptedData);
    }
}