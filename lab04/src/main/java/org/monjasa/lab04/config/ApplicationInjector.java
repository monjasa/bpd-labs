package org.monjasa.lab04.config;

import com.google.inject.AbstractModule;
import lombok.NoArgsConstructor;
import org.monjasa.lab04.crypto.key.RsaKeyFactory;
import org.monjasa.lab04.crypto.key.RsaKeyPairGenerator;
import org.monjasa.lab04.crypto.key.impl.RsaKeyFactoryImpl;
import org.monjasa.lab04.crypto.key.impl.RsaKeyPairGeneratorImpl;
import org.monjasa.lab04.service.EncryptionService;
import org.monjasa.lab04.service.FileService;
import org.monjasa.lab04.service.impl.EncryptionServiceImpl;
import org.monjasa.lab04.service.impl.FileServiceImpl;
import org.monjasa.lab04.strategy.impl.FileDecryptionStrategy;
import org.monjasa.lab04.strategy.impl.FileEncryptionStrategy;
import org.monjasa.lab04.strategy.impl.KeyGenerationStrategy;
import org.monjasa.lab04.utils.CommandLineMapper;

@NoArgsConstructor(staticName = "getInstance")
public class ApplicationInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(KeyGenerationStrategy.class);
        bind(FileEncryptionStrategy.class);
        bind(FileDecryptionStrategy.class);
        bind(CommandLineMapper.class);

        bind(FileService.class).to(FileServiceImpl.class);
        bind(EncryptionService.class).to(EncryptionServiceImpl.class);

        bind(RsaKeyFactory.class).to(RsaKeyFactoryImpl.class);
        bind(RsaKeyPairGenerator.class).to(RsaKeyPairGeneratorImpl.class);
    }
}
