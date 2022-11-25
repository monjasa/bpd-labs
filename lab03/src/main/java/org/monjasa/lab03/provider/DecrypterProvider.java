package org.monjasa.lab03.provider;

import org.monjasa.lab03.crypto.cipher.decrypter.Decrypter;

public interface DecrypterProvider {

    Decrypter provideInstance();

}
