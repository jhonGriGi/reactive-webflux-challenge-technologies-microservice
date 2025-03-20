package co.com.bancolombia.config;

import co.com.bancolombia.r2dbc.technologies.TechnologyReactiveAdapter;
import co.com.bancolombia.r2dbc.technologies.TechnologyReactiveRepository;
import co.com.bancolombia.usecase.register.RegisterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
        @Bean
        public RegisterUseCase registerUseCase() {
                return new RegisterUseCase(new TechnologyReactiveAdapter(ReactiveRepo));
        }
}
