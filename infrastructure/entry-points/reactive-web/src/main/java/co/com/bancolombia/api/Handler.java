package co.com.bancolombia.api;

import co.com.bancolombia.api.dtos.register.RegisterValidator;
import co.com.bancolombia.api.dtos.search.SearchValidator;
import co.com.bancolombia.model.technology.Technology;
import co.com.bancolombia.api.dtos.register.RegisterDTO;
import co.com.bancolombia.usecase.register.RegisterUseCase;
import co.com.bancolombia.api.dtos.search.SearchDTO;
import co.com.bancolombia.usecase.search.SearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
    private final String DEFAULT_OFFSET = "0";
    private final String DEFAULT_LIMIT = "10";
    private final RegisterUseCase registerUseCase;
    private final SearchUseCase searchUseCase;
    private final SearchValidator searchValidator;
    private final RegisterValidator registerValidator;

    public Mono<ServerResponse> search(ServerRequest serverRequest) {
        SearchDTO searchDTO = new SearchDTO();
        BindingResult bindingResult = new BeanPropertyBindingResult(searchDTO, "searchDTO");

        searchDTO.setOrderBy(serverRequest.queryParam("orderBy").orElse(null));
        searchDTO.setOffset(Integer.parseInt(serverRequest.queryParam("offset").orElse(DEFAULT_OFFSET)));
        searchDTO.setLimit(Integer.parseInt(serverRequest.queryParam("limit").orElse(DEFAULT_LIMIT)));

        this.searchValidator.validate(searchDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return ServerResponse.badRequest().bodyValue(bindingResult.getAllErrors());
        }

        Flux<Technology> response = this.searchUseCase.search(searchDTO.getOrderBy(), searchDTO.getOffset(), searchDTO.getLimit());

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(response, Technology.class);
    }


    public Mono<ServerResponse> register(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(RegisterDTO.class)
                .flatMap(registerDTO -> {
                    BindingResult bindingResult = new BeanPropertyBindingResult(registerDTO, "registerDTO");

                    this.registerValidator.validate(registerDTO, bindingResult);

                    if (bindingResult.hasErrors()) {
                        return ServerResponse.badRequest().bodyValue(bindingResult.getAllErrors());
                    }

                    Mono<Technology> response = this.registerUseCase.execute(Mono.just(registerDTO.toModel()));

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(response, Technology.class);
                })
                .onErrorResume(e -> ServerResponse.status(500).bodyValue(e.getMessage()));
    }
}
