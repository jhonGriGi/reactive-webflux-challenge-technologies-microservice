package co.com.bancolombia.usecase.register;

import co.com.bancolombia.model.technology.Technology;
import co.com.bancolombia.model.technology.gateways.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class RegisterUseCase {
    private final TechnologyRepository repository;

    public Mono<Technology> execute(Mono<Technology> technology) {
        return technology.flatMap(this.repository::register);
    }
}
