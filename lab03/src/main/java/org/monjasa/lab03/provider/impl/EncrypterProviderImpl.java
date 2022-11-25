package org.monjasa.lab03.provider.impl;

import org.monjasa.lab03.crypto.cipher.encrypter.Encrypter;
import org.monjasa.lab03.crypto.cipher.encrypter.impl.LeadIvBlockEncrypter;
import org.monjasa.lab03.crypto.cipher.engine.buffered.impl.PaddedBufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.CbcCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.Rc5CipherEngine;
import org.monjasa.lab03.provider.EncrypterProvider;

import javax.inject.Singleton;

@Singleton
public class EncrypterProviderImpl implements EncrypterProvider {

    @Override
    public Encrypter provideInstance() {
        return new LeadIvBlockEncrypter(
                new Rc5CipherEngine(),
                new PaddedBufferedBlockCipherEngine(new CbcCipherEngine(new Rc5CipherEngine()))
        );
    }
}
