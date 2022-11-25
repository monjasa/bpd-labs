package org.monjasa.lab03.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.monjasa.lab03.crypto.cipher.encrypter.Encrypter;
import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;
import org.monjasa.lab03.provider.CipherParametersProvider;
import org.monjasa.lab03.provider.EncrypterProvider;
import org.monjasa.lab03.service.EncryptionService;
import org.monjasa.lab03.service.IvGenerationService;
import org.monjasa.lab03.service.KeyGenerationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class EncryptionServiceImpl implements EncryptionService {

    @Inject
    private KeyGenerationService keyGenerationService;

    @Inject
    private IvGenerationService ivGenerationService;

    @Inject
    private EncrypterProvider encrypterProvider;

    @Inject
    private CipherParametersProvider cipherParametersProvider;

    @Override
    public byte[] encrypt(byte[] data, String key) {
        Encrypter encrypter = encrypterProvider.provideInstance();
        CipherParameters cipherParameters = cipherParametersProvider.provideEncryptionParameters(
                keyGenerationService.generateKey(key),
                ivGenerationService.generateIv(encrypter.getIvBlockSize())
        );

        encrypter.initialize(cipherParameters);

        return encrypter.encrypt(data);
    }
}
