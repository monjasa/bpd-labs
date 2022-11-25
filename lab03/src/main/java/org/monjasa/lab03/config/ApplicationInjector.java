package org.monjasa.lab03.config;

import com.google.inject.AbstractModule;
import lombok.NoArgsConstructor;
import org.monjasa.lab01.provider.LinearCongruentialGeneratorProvider;
import org.monjasa.lab01.provider.impl.LinearCongruentialGeneratorProviderImpl;
import org.monjasa.lab01.service.LinearCongruentialGeneratorService;
import org.monjasa.lab01.service.impl.LinearCongruentialGeneratorServiceImpl;
import org.monjasa.lab02.service.MessageDigestService;
import org.monjasa.lab02.service.impl.MessageDigestServiceImpl;
import org.monjasa.lab03.provider.CipherParametersProvider;
import org.monjasa.lab03.provider.DecrypterProvider;
import org.monjasa.lab03.provider.EncrypterProvider;
import org.monjasa.lab03.provider.impl.CipherParametersProviderImpl;
import org.monjasa.lab03.provider.impl.DecrypterProviderImpl;
import org.monjasa.lab03.provider.impl.EncrypterProviderImpl;
import org.monjasa.lab03.service.*;
import org.monjasa.lab03.service.impl.*;
import org.monjasa.lab03.strategy.impl.FileDecryptionStrategy;
import org.monjasa.lab03.strategy.impl.FileEncryptionStrategy;
import org.monjasa.lab03.utils.CommandLineMapper;

@NoArgsConstructor(staticName = "getInstance")
public class ApplicationInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(FileEncryptionStrategy.class);
        bind(FileDecryptionStrategy.class);
        bind(CommandLineMapper.class);

        bind(FileService.class).to(FileServiceImpl.class);
        bind(EncryptionService.class).to(EncryptionServiceImpl.class);
        bind(DecryptionService.class).to(DecryptionServiceImpl.class);
        bind(IvGenerationService.class).to(IvGenerationServiceImpl.class);
        bind(KeyGenerationService.class).to(KeyGenerationServiceImpl.class);

        bind(EncrypterProvider.class).to(EncrypterProviderImpl.class);
        bind(DecrypterProvider.class).to(DecrypterProviderImpl.class);
        bind(CipherParametersProvider.class).to(CipherParametersProviderImpl.class);

        bind(MessageDigestService.class).to(MessageDigestServiceImpl.class);
        bind(LinearCongruentialGeneratorProvider.class).to(LinearCongruentialGeneratorProviderImpl.class);
        bind(LinearCongruentialGeneratorService.class).to(LinearCongruentialGeneratorServiceImpl.class);
    }
}
