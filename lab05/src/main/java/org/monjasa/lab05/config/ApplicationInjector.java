package org.monjasa.lab05.config;

import com.google.inject.AbstractModule;
import lombok.NoArgsConstructor;
import org.monjasa.lab05.crypto.key.DsaKeyFactory;
import org.monjasa.lab05.crypto.key.DsaKeyPairGenerator;
import org.monjasa.lab05.crypto.key.impl.DsaKeyFactoryImpl;
import org.monjasa.lab05.crypto.key.impl.DsaKeyPairGeneratorImpl;
import org.monjasa.lab05.service.FileService;
import org.monjasa.lab05.service.SignatureService;
import org.monjasa.lab05.service.impl.FileServiceImpl;
import org.monjasa.lab05.service.impl.SignatureServiceImpl;
import org.monjasa.lab05.strategy.impl.FileSigningStrategy;
import org.monjasa.lab05.strategy.impl.FileVerificationStrategy;
import org.monjasa.lab05.strategy.impl.KeyGenerationStrategy;
import org.monjasa.lab05.utils.CommandLineMapper;

@NoArgsConstructor(staticName = "getInstance")
public class ApplicationInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(KeyGenerationStrategy.class);
        bind(FileVerificationStrategy.class);
        bind(FileSigningStrategy.class);
        bind(CommandLineMapper.class);

        bind(FileService.class).to(FileServiceImpl.class);
        bind(SignatureService.class).to(SignatureServiceImpl.class);

        bind(DsaKeyFactory.class).to(DsaKeyFactoryImpl.class);
        bind(DsaKeyPairGenerator.class).to(DsaKeyPairGeneratorImpl.class);
    }
}
