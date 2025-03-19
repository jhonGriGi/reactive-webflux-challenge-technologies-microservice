package co.com.bancolombia.model.technology.gateways;

import co.com.bancolombia.model.technology.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyRepository {
    Flux<Technology> search(String orderBy, int offset, int limit);
    Mono<Technology> register(Technology technology);
}
