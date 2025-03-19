package co.com.bancolombia.r2dbc.technologies;

import co.com.bancolombia.model.technology.Technology;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

public interface TechnologyReactiveRepository extends
        ReactiveCrudRepository<TechnologyEntity, String>,
        ReactiveQueryByExampleExecutor<TechnologyEntity> {
    default Flux<Technology> searchPaginated(String orderBy, int offset, int limit) {
        String query = "SELECT * FROM technology ORDER BY " + orderBy + " LIMIT " + limit + " OFFSET " + offset;

        return
    };
}
