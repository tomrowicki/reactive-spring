package guru.springframework.netfluxexample.service;

import guru.springframework.netfluxexample.model.Quote;
import guru.springframework.netfluxexample.model.QuoteEntity;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<QuoteEntity> fetchQuoteStream(Duration period);
}
