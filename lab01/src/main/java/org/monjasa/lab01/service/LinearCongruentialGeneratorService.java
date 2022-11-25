package org.monjasa.lab01.service;

import org.monjasa.lab01.model.LinearCongruentialGenerator;

public interface LinearCongruentialGeneratorService {

    long[] generateLongValues(LinearCongruentialGenerator generator, int length);

    byte[] generateByteValues(LinearCongruentialGenerator generator, int length);

    long determinePeriodLength(LinearCongruentialGenerator generator);

}
