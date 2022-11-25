package org.monjasa.lab03.service.impl;

import org.monjasa.lab03.crypto.cipher.decrypter.Decrypter;
import org.monjasa.lab03.crypto.cipher.encrypter.Encrypter;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.provider.CipherParametersProvider;
import org.monjasa.lab03.provider.DecrypterProvider;
import org.monjasa.lab03.service.DecryptionService;
import org.monjasa.lab03.service.KeyGenerationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DecryptionServiceImpl implements DecryptionService {

    @Inject
    private KeyGenerationService keyGenerationService;

    @Inject
    private DecrypterProvider decrypterProvider;

    @Inject
    private CipherParametersProvider cipherParametersProvider;

    @Override
    public byte[] decrypt(byte[] data, String key) {
        Decrypter decrypter = decrypterProvider.provideInstance();
        CipherParameters cipherParameters = cipherParametersProvider.provideDecryptionParameters(
                keyGenerationService.generateKey(key)
        );

        decrypter.initialize(cipherParameters);

        return decrypter.decrypt(data);
    }
}
