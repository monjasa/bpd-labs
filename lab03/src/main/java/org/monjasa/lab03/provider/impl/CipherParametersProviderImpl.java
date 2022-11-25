package org.monjasa.lab03.provider.impl;

import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.crypto.cipher.parameters.impl.CipherParametersWithIv;
import org.monjasa.lab03.crypto.cipher.parameters.impl.Rc5CipherParameters;
import org.monjasa.lab03.provider.CipherParametersProvider;

import javax.inject.Singleton;

@Singleton
public class CipherParametersProviderImpl implements CipherParametersProvider {

    public static final int NUMBER_OF_ROUNDS = 12;

    @Override
    public CipherParameters provideEncryptionParameters(byte[] key, byte[] iv) {
        return new CipherParametersWithIv(new Rc5CipherParameters(key, NUMBER_OF_ROUNDS), iv);
    }

    @Override
    public CipherParameters provideDecryptionParameters(byte[] key) {
        return new Rc5CipherParameters(key, NUMBER_OF_ROUNDS);
    }
}
