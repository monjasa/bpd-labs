package org.monjasa.lab02.config;

import com.google.inject.AbstractModule;
import lombok.NoArgsConstructor;
import org.monjasa.lab02.service.FileService;
import org.monjasa.lab02.service.MessageDigestService;
import org.monjasa.lab02.service.impl.FileServiceImpl;
import org.monjasa.lab02.service.impl.MessageDigestServiceImpl;
import org.monjasa.lab02.strategy.impl.FileCheckingStrategy;
import org.monjasa.lab02.strategy.impl.HashingStrategy;

@NoArgsConstructor(staticName = "getInstance")
public class ApplicationInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(HashingStrategy.class);
        bind(FileCheckingStrategy.class);

        bind(MessageDigestService.class).to(MessageDigestServiceImpl.class);
        bind(FileService.class).to(FileServiceImpl.class);
    }
}
