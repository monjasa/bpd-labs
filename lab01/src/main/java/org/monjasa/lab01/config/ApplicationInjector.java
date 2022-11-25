package org.monjasa.lab01.config;

import com.google.inject.AbstractModule;
import lombok.NoArgsConstructor;
import org.monjasa.lab01.service.FileService;
import org.monjasa.lab01.service.LinearCongruentialGeneratorService;
import org.monjasa.lab01.service.impl.FileServiceImpl;
import org.monjasa.lab01.service.impl.LinearCongruentialGeneratorServiceImpl;
import org.monjasa.lab01.strategy.impl.NumberGenerationStrategy;
import org.monjasa.lab01.strategy.impl.PeriodDeterminationStrategy;

@NoArgsConstructor(staticName = "getInstance")
public class ApplicationInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(NumberGenerationStrategy.class);
        bind(PeriodDeterminationStrategy.class);

        bind(LinearCongruentialGeneratorService.class).to(LinearCongruentialGeneratorServiceImpl.class);
        bind(FileService.class).to(FileServiceImpl.class);
    }
}
