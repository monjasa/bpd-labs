package org.monjasa.lab05.crypto.key.pair;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

@Getter
@Setter
@Builder
public class DsaKeyPair {

    private DSAPublicKey publicKey;

    private DSAPrivateKey privateKey;

}
