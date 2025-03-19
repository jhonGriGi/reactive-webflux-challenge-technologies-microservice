package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.technology.Technology;
import co.com.bancolombia.r2dbc.technologies.TechnologyEntity;
import co.com.bancolombia.r2dbc.technologies.TechnologyReactiveAdapter;
import co.com.bancolombia.r2dbc.technologies.TechnologyReactiveRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyReactiveRepositoryAdapterTest {

    TechnologyEntity entity = new TechnologyEntity();

    @InjectMocks
    TechnologyReactiveAdapter repositoryAdapter;

    @Mock
    TechnologyReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    @Test
    void mustFindValueById() {
        entity.setId("1");
        entity.setName("name");
        entity.setDescription("description");

        Technology expectedTechnology = Technology.builder()
                .name("name")
                .description("description")
                .build();

        // Mocking repository and mapper
        when(repository.findById("1")).thenReturn(Mono.just(entity));
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Mono<Technology> result = repositoryAdapter.findById("1");

        StepVerifier.create(result)
                .expectNext(expectedTechnology)  // Directly comparing the expected Technology object
                .verifyComplete();
    }

    @Test
    void mustFindAllValues() {
        entity.setId("1");
        entity.setName("name");
        entity.setDescription("description");

        Technology expectedTechnology = Technology.builder()
                .name("name")
                .description("description")
                .build();

        // Mocking repository and mapper
        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Flux<Technology> result = repositoryAdapter.findAll();

        StepVerifier.create(result)
                .expectNext(expectedTechnology)  // Directly comparing the expected Technology object
                .verifyComplete();
    }

    @Test
    void mustFindByExample() {
        Technology model = Technology.builder()
                .name("model")
                .description("description")
                .build();

        Technology expectedTechnology = Technology.builder()
                .name("model")
                .description("description")
                .build();

        // Mocking repository and mapper
        when(repository.findAll(any(Example.class))).thenReturn(Flux.just(entity));
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Flux<Technology> result = repositoryAdapter.findByExample(model);

        StepVerifier.create(result)
                .expectNext(expectedTechnology)  // Directly comparing the expected Technology object
                .verifyComplete();
    }

    @Test
    void mustSaveValue() {
        entity.setId("1");
        entity.setName("name");
        entity.setDescription("description");

        Technology model = Technology.builder()
                .name("model")
                .description("description")
                .build();

        Technology expectedTechnology = Technology.builder()
                .name("name")
                .description("description")
                .build();

        // Mocking repository and mapper
        when(repository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.map(model, TechnologyEntity.class)).thenReturn(entity);
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Mono<Technology> result = repositoryAdapter.save(model);

        StepVerifier.create(result)
                .expectNext(expectedTechnology)  // Directly comparing the expected Technology object
                .verifyComplete();
    }
}
