package org.monjasa.lab03.provider;

import org.monjasa.lab03.crypto.cipher.encrypter.Encrypter;

public interface EncrypterProvider {

    Encrypter provideInstance();

}
