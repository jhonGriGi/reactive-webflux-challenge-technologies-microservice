package co.com.bancolombia.api.config;

import co.com.bancolombia.api.dtos.register.RegisterValidator;
import co.com.bancolombia.api.dtos.search.SearchValidator;
import co.com.bancolombia.model.technology.gateways.TechnologyRepository;
import co.com.bancolombia.usecase.register.RegisterUseCase;
import co.com.bancolombia.usecase.search.SearchUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {

    @Bean
    public RegisterValidator registerValidator() {
        return new RegisterValidator();
    }

    @Bean
    public SearchValidator searchValidator() {
        return new SearchValidator();
    }

    @Bean
    public SearchUseCase searchUseCase(TechnologyRepository technologyRepository) {
        return new SearchUseCase(technologyRepository);
    }

    @Bean
    public RegisterUseCase registerUseCase(TechnologyRepository technologyRepository) {
        return new RegisterUseCase(technologyRepository);
    }
}
