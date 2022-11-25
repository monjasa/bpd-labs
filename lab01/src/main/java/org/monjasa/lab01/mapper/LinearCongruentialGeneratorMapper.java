package org.monjasa.lab01.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.configuration2.ImmutableConfiguration;
import org.monjasa.lab01.model.LinearCongruentialGenerator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LinearCongruentialGeneratorMapper {

    public static LinearCongruentialGenerator fromConfiguration(ImmutableConfiguration configuration) {
        return LinearCongruentialGenerator.builder()
                .modulus(configuration.getLong("m"))
                .multiplier(configuration.getLong("a"))
                .addend(configuration.getLong("c"))
                .seed(configuration.getLong("x0"))
                .build();
    }
}
