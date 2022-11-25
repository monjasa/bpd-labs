package org.monjasa.lab03.provider;

import org.monjasa.lab03.crypto.cipher.parameters.CipherParameters;

public interface CipherParametersProvider {

    CipherParameters provideEncryptionParameters(byte[] key, byte[] iv);

    CipherParameters provideDecryptionParameters(byte[] key);

}
