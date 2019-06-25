package guru.springframework.netfluxexample;

import guru.springframework.netfluxexample.client.StockQuoteClient;
import guru.springframework.netfluxexample.model.QuoteEntity;
import guru.springframework.netfluxexample.repositories.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

// used for client testing
@Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient client;

    private final QuoteRepository repository;

    public QuoteRunner(StockQuoteClient client, QuoteRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<QuoteEntity> quoteFlux = repository.findWithTailableCursorBy();
        System.out.println("Attempting tailing procedure.");
        // allows to dispose of the connection object once the stream finishes
        CountDownLatch latch = new CountDownLatch(1);
        Disposable disposable = quoteFlux.subscribe(quote ->
            System.out.println("*@^@^@^@^@^@^@^@^@^@ Id: " + quote.getId()),
            System.err::println,
            latch::countDown);

        latch.await();
        disposable.dispose();
    }
}
