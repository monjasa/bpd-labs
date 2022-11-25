package org.monjasa.lab03.service.impl;

import org.monjasa.lab01.model.LinearCongruentialGenerator;
import org.monjasa.lab01.provider.LinearCongruentialGeneratorProvider;
import org.monjasa.lab01.service.LinearCongruentialGeneratorService;
import org.monjasa.lab03.service.IvGenerationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IvGenerationServiceImpl implements IvGenerationService {

    @Inject
    private LinearCongruentialGeneratorService generatorService;

    @Inject
    private LinearCongruentialGeneratorProvider generatorProvider;

    @Override
    public byte[] generateIv(int length) {
        LinearCongruentialGenerator generator = generatorProvider.provideInstance();
        return generatorService.generateByteValues(generator, length);
    }
}
