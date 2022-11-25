package org.monjasa.lab01.provider.impl;

import org.monjasa.lab01.model.LinearCongruentialGenerator;
import org.monjasa.lab01.provider.LinearCongruentialGeneratorProvider;

import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class LinearCongruentialGeneratorProviderImpl implements LinearCongruentialGeneratorProvider {

    private static final long OPTIMAL_MODULUS = (1L << 31) - 1;

    private static final long OPTIMAL_MULTIPLIER = (long) Math.pow(7, 5);

    private static final long OPTIMAL_ADDEND = 0;

    private final AtomicLong seed = new AtomicLong(System.nanoTime());

    @Override
    public LinearCongruentialGenerator provideInstance() {
        return LinearCongruentialGenerator.builder()
                .modulus(OPTIMAL_MODULUS)
                .multiplier(OPTIMAL_MULTIPLIER)
                .addend(OPTIMAL_ADDEND)
                .seed(seed.getAndUpdate(previousValue -> previousValue ^ System.nanoTime()))
                .build();
    }
}
