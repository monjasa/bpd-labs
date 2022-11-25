package org.monjasa.lab01.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LinearCongruentialGenerator {

    private long modulus;

    private long multiplier;

    private long addend;

    private long seed;

}
