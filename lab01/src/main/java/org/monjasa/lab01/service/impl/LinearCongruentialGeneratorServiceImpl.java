package org.monjasa.lab01.service.impl;

import org.monjasa.lab01.model.LinearCongruentialGenerator;
import org.monjasa.lab01.service.LinearCongruentialGeneratorService;

import javax.inject.Singleton;

@Singleton
public class LinearCongruentialGeneratorServiceImpl implements LinearCongruentialGeneratorService {

    @Override
    public long[] generateLongValues(LinearCongruentialGenerator generator, int length) {
        long[] values = new long[length];

        values[0] = generator.getSeed();
        for (int i = 1; i < length; i++) {
            values[i] = (generator.getMultiplier() * values[i - 1] + generator.getAddend()) % generator.getModulus();
        }

        return values;
    }

    @Override
    public byte[] generateByteValues(LinearCongruentialGenerator generator, int length) {
        byte[] values = new byte[length];

        values[0] = (byte) generator.getSeed();
        for (int i = 1; i < length; i++) {
            values[i] = (byte) ((generator.getMultiplier() * values[i - 1] + generator.getAddend()) % generator.getModulus());
        }

        return values;
    }

    @Override
    public long determinePeriodLength(LinearCongruentialGenerator generator) {
        long value = generator.getSeed();
        long periodLength = 0;

        do {
            value = (generator.getMultiplier() * value + generator.getAddend()) % generator.getModulus();
            periodLength++;
        } while (value != generator.getSeed());
        
        return periodLength;
    }
}
