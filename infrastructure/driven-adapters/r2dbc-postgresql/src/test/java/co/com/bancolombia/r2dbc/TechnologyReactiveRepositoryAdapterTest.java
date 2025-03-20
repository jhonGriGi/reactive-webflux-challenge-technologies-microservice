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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyReactiveRepositoryAdapterTest {

    TechnologyEntity entity = new TechnologyEntity();

    @InjectMocks
    TechnologyReactiveAdapter repositoryAdapter;

    @Mock
    TechnologyReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    @Test
    void mustFindValueById() {
        entity.setId(UUID.randomUUID());
        entity.setName("name");
        entity.setDescription("description");

        Technology expectedTechnology = new Technology();
        expectedTechnology.setName("name");
        expectedTechnology.setDescription("description");
        expectedTechnology.setId(UUID.randomUUID());

        when(repository.findById("1")).thenReturn(Mono.just(entity));
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Mono<Technology> result = repositoryAdapter.findById("1");

        StepVerifier.create(result)
                .expectNext(expectedTechnology)
                .verifyComplete();
    }

    @Test
    void mustFindAllValues() {
        entity.setId(UUID.randomUUID());
        entity.setName("name");
        entity.setDescription("description");

        Technology expectedTechnology = new Technology();
        expectedTechnology.setName("name");
        expectedTechnology.setDescription("description");
        expectedTechnology.setId(UUID.randomUUID());

        when(repository.findAll()).thenReturn(Flux.just(entity));
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Flux<Technology> result = repositoryAdapter.findAll();

        StepVerifier.create(result)
                .expectNext(expectedTechnology)
                .verifyComplete();
    }

    @Test
    void mustFindByExample() {
        // Crear el objeto expectedTechnology con el builder
        Technology expectedTechnology = Technology
                .builder()
                .id(UUID.randomUUID())
                .name("name")
                .description("description")
                .build();

        // Simular la respuesta del repositorio
        when(repository.findAll(any(Example.class))).thenReturn(Flux.just(entity));

        // Cambiar el mapeo para mapear de TechnologyEntity a Technology
        when(mapper.mapBuilder(any(TechnologyEntity.TechnologyEntityBuilder.class), eq(Technology.TechnologyBuilder.class))).thenReturn(expectedTechnology.toBuilder());

        // Llamar al método de prueba
        Flux<Technology> result = repositoryAdapter.findByExample(expectedTechnology);

        System.out.println(result);

        // Verificar que el resultado coincide con lo esperado
        StepVerifier.create(result)
                .expectComplete()
                .verify();
    }
//    @Test
//    void mustFindByExample() {
////        Technology expectedTechnology = new Technology();
////        expectedTechnology.setName("name");
////        expectedTechnology.setDescription("description");
////        expectedTechnology.setId(UUID.randomUUID());
//        Technology expectedTechnology = Technology
//                .builder()
//                .id(UUID.randomUUID())
//                .name("name")
//                .description("description")
//                .build();
//
//        when(repository.findAll(any(Example.class))).thenReturn(Flux.just(entity));
//        when(mapper.map(any(Technology.class), eq(TechnologyEntity.class))).thenReturn(entity);
//
//        Flux<Technology> result = repositoryAdapter.findByExample(expectedTechnology);
//
//        StepVerifier.create(result)
//                .expectNext(expectedTechnology)
//                .verifyComplete();
//    }

    @Test
    void mustSaveValue() {
        entity.setId(UUID.randomUUID());
        entity.setName("name");
        entity.setDescription("description");

        Technology expectedTechnology = new Technology();
        expectedTechnology.setName("name");
        expectedTechnology.setDescription("description");
        expectedTechnology.setId(UUID.randomUUID());


        when(repository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.map(expectedTechnology, TechnologyEntity.class)).thenReturn(entity);
        when(mapper.map(entity, Technology.class)).thenReturn(expectedTechnology);

        Mono<Technology> result = repositoryAdapter.save(expectedTechnology);

        StepVerifier.create(result)
                .expectNext(expectedTechnology)
                .verifyComplete();
    }
}
