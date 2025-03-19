package co.com.bancolombia.r2dbc.technologies;

import co.com.bancolombia.model.technology.Technology;
import co.com.bancolombia.model.technology.gateways.TechnologyRepository;
import co.com.bancolombia.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TechnologyReactiveAdapter extends ReactiveAdapterOperations<
        Technology,
        TechnologyEntity,
        String,
        TechnologyReactiveRepository> implements TechnologyRepository {
    public TechnologyReactiveAdapter(TechnologyReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Technology.TechnologyBuilder.class).build());
    }

    @Override
    public Flux<Technology> search(String orderBy, int offset, int limit) {
        return this.repository.searchPaginated(orderBy, offset, limit);
    }

    @Override
    public Mono<Technology> register(Technology technology) {
        return super.save(technology);
    }
}
