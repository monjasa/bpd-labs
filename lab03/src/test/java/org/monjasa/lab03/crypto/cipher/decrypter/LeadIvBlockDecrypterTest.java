package org.monjasa.lab03.crypto.cipher.decrypter;

import org.junit.jupiter.api.Test;
import org.monjasa.lab03.crypto.cipher.decrypter.Decrypter;
import org.monjasa.lab03.crypto.cipher.decrypter.impl.LeadIvBlockDecrypter;
import org.monjasa.lab03.crypto.cipher.engine.buffered.impl.PaddedBufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.CbcCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.Rc5CipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.impl.Rc5CipherParameters;
import org.monjasa.lab03.crypto.encoder.base.Hex;
import org.monjasa.lab03.crypto.utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

class LeadIvBlockDecrypterTest {

    @Test
    void testDecryption() {
        Decrypter decrypter = new LeadIvBlockDecrypter(
                new Rc5CipherEngine(),
                new PaddedBufferedBlockCipherEngine(new CbcCipherEngine(new Rc5CipherEngine()))
        );

        decrypter.initialize(new Rc5CipherParameters(Hex.decode("00"), 12));

        byte[] decryptedData = decrypter.decrypt(Hex.decode("462955bde36a099788c715e057953f725eb9cce32e4883387fb32a6bfaa4f03e13793f76620798925edc8c6336d81fe5"));

        assertThat(decryptedData).isEqualTo(Hex.decode("ffffffffffffffffffffffffffffffffaa"));
    }
}