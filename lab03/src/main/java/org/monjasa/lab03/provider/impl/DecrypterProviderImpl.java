package org.monjasa.lab03.provider.impl;

import org.monjasa.lab03.crypto.cipher.decrypter.Decrypter;
import org.monjasa.lab03.crypto.cipher.decrypter.impl.LeadIvBlockDecrypter;
import org.monjasa.lab03.crypto.cipher.engine.buffered.impl.PaddedBufferedBlockCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.CbcCipherEngine;
import org.monjasa.lab03.crypto.cipher.engine.impl.Rc5CipherEngine;
import org.monjasa.lab03.provider.DecrypterProvider;

import javax.inject.Singleton;

@Singleton
public class DecrypterProviderImpl implements DecrypterProvider {

    @Override
    public Decrypter provideInstance() {
        return new LeadIvBlockDecrypter(
                new Rc5CipherEngine(),
                new PaddedBufferedBlockCipherEngine(new CbcCipherEngine(new Rc5CipherEngine()))
        );
    }
}
