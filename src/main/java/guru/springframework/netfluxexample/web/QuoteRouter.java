package guru.springframework.netfluxexample.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class QuoteRouter {

    @Bean
    public RouterFunction<ServerResponse> route(QuoteHandler handler) {
        return RouterFunctions
                .route(GET("/quotes")
                .and(accept(MediaType.APPLICATION_JSON)), handler::fetchQuotes)
                .andRoute(GET("/quotes").and(accept(MediaType.APPLICATION_STREAM_JSON)), handler::streamQuotes);
    }


}
