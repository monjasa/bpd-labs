package org.monjasa.lab04.crypto.key.pair;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Getter
@Setter
@Builder
public class RsaKeyPair {

    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;

}
