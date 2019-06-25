package guru.springframework.netfluxexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Document
@NoArgsConstructor
public class QuoteEntity {

    @Id
    private String id;

    private String ticker;

    private BigDecimal price;

    private Instant instant;

    public QuoteEntity(String ticker, Double price) {
        this.ticker = ticker;
        this.price = new BigDecimal(price);
        this.instant = Instant.now();
    }

    public QuoteEntity(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
        this.instant = Instant.now();
    }
}
