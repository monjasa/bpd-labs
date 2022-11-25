package org.monjasa.lab05.crypto.key;

import java.security.Key;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

public interface DsaKeyFactory {

    byte[] encodeKey(Key key);

    DSAPublicKey decodePublicKey(byte[] encodedPublicKey);

    DSAPrivateKey decodePrivateKey(byte[] encodedPrivateKey);

}
