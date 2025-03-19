package co.com.bancolombia.usecase.search;

import co.com.bancolombia.model.technology.Technology;
import co.com.bancolombia.model.technology.gateways.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class SearchUseCase {
    private final TechnologyRepository repository;

    public Flux<Technology> search(String orderBy, int limit, int offset) {
        return this.repository.search(orderBy, limit, offset);
    }
}
