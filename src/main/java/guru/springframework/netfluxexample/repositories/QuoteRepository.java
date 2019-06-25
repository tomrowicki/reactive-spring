package guru.springframework.netfluxexample.repositories;

import guru.springframework.netfluxexample.model.QuoteEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface QuoteRepository extends ReactiveMongoRepository<QuoteEntity, String> {

    // reacts to every addition event
    @Tailable
    public Flux<QuoteEntity> findWithTailableCursorBy();
}
