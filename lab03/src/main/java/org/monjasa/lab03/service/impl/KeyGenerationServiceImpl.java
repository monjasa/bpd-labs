package org.monjasa.lab03.service.impl;

import org.monjasa.lab02.service.MessageDigestService;
import org.monjasa.lab03.service.KeyGenerationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.ByteBuffer;

@Singleton
public class KeyGenerationServiceImpl implements KeyGenerationService {

    @Inject
    private MessageDigestService messageDigestService;

    @Override
    public byte[] generateKey(String key) {
        byte[] digestHigh = messageDigestService.digest(key.getBytes());
        byte[] digestLow = messageDigestService.digest(digestHigh);

        return ByteBuffer.allocate(2 * messageDigestService.getDigestLength())
                .put(digestHigh)
                .put(digestLow)
                .array();
    }
}
