package co.com.bancolombia.r2dbc.technologies;

import co.com.bancolombia.model.technology.Technology;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TechnologyReactiveRepository extends
        ReactiveCrudRepository<TechnologyEntity, String>,
        ReactiveQueryByExampleExecutor<TechnologyEntity> {

    @Query("SELECT * FROM technologies t ORDER BY t.name ASC LIMIT CAST(:limit AS BIGINT) OFFSET CAST(:offset AS BIGINT)")
     Flux<Technology> findAllByASC(@Param("limit") int limit, @Param("offset") int offset);

     @Query("SELECT * FROM technologies t ORDER BY t.name DESC LIMIT CAST(:limit AS BIGINT) OFFSET CAST(:offset AS BIGINT)")
     Flux<Technology> findAllByDesc(@Param("limit") int limit, @Param("offset") int offset);
}
