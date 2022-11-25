package org.monjasa.lab03.crypto.cipher.encrypter;

import org.junit.jupiter.api.Test;
import org.monjasa.lab03.crypto.cipher.encrypter.Encrypter;
import org.monjasa.lab03.crypto.cipher.engine.buffered.impl.PaddedBufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.CbcCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.Rc5CipherEngine;
import org.monjasa.lab03.crypto.cipher.parameters.impl.CipherParametersWithIv;
import org.monjasa.lab03.crypto.cipher.parameters.impl.Rc5CipherParameters;
import org.monjasa.lab03.crypto.encoder.base.Hex;
import org.monjasa.lab03.crypto.cipher.encrypter.impl.LeadIvBlockEncrypter;
import org.monjasa.lab03.crypto.utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

class LeadIvBlockEncrypterTest {

    @Test
    void testEncryption() {
        Encrypter encrypter = new LeadIvBlockEncrypter(
                new Rc5CipherEngine(),
                new PaddedBufferedBlockCipherEngine(new CbcCipherEngine(new Rc5CipherEngine()))
        );

        encrypter.initialize(new CipherParametersWithIv(
                new Rc5CipherParameters(Hex.decode("00"), 12),
                Hex.decode("00000000000000000000000000000000")
        ));

        byte[] encryptedData = encrypter.encrypt(Hex.decode("ffffffffffffffffffffffffffffffffaa"));

        assertThat(encryptedData)
                .isEqualTo(Hex.decode("462955bde36a099788c715e057953f725eb9cce32e4883387fb32a6bfaa4f03e13793f76620798925edc8c6336d81fe5"));
    }
}