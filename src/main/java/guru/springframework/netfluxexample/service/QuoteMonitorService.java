package guru.springframework.netfluxexample.service;

import guru.springframework.netfluxexample.client.StockQuoteClient;
import guru.springframework.netfluxexample.model.QuoteEntity;
import guru.springframework.netfluxexample.repositories.QuoteRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by jt on 10/19/17.
 */
@Service
public class QuoteMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    private final StockQuoteClient stockQuoteClient;
    private final QuoteRepository quoteRepository;

    public QuoteMonitorService(StockQuoteClient stockQuoteClient, QuoteRepository quoteRepository) {
        this.stockQuoteClient = stockQuoteClient;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        stockQuoteClient.getQuoteStream()
                .subscribe(quote -> {
                    Mono<QuoteEntity> savedQuote = quoteRepository.save(quote);
                    savedQuote.subscribe(quoteEntity -> System.out.println("I saved a quote! Id: " + quoteEntity.getId()));
                });
    }
}
