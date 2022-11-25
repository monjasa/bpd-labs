package org.monjasa.lab04.crypto.key;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public interface RsaKeyFactory {

    byte[] encodeKey(Key key);

    RSAPublicKey decodePublicKey(byte[] encodedPublicKey);

    RSAPrivateKey decodePrivateKey(byte[] encodedPrivateKey);

}
