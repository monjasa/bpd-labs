package org.monjasa.lab03.crypto.cipher.engine;

import org.junit.jupiter.api.Test;
import org.monjasa.lab03.crypto.cipher.engine.impl.Rc5CipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.Rc5CipherParameters;
import org.monjasa.lab03.crypto.encoder.base.Hex;

import static org.assertj.core.api.Assertions.assertThat;

class Rc5CipherEngineTest {

    @Test
    void testCipherEngine() {
        byte[] data = Hex.decode("00000000000000000000000000000000");
        CipherParameters parameters = new Rc5CipherParameters(Hex.decode("00"), 0);

        Rc5CipherEngine cipherEngine = new Rc5CipherEngine();

        cipherEngine.initialize(true, parameters);
        byte[] encryptedData = new byte[cipherEngine.getBlockSize()];
        cipherEngine.processBlock(data, 0, encryptedData, 0);

        cipherEngine.initialize(false, parameters);
        byte[] decryptedData = new byte[cipherEngine.getBlockSize()];
        cipherEngine.processBlock(encryptedData, 0, decryptedData, 0);

        assertThat(data).isEqualTo(decryptedData);
    }
}