package guru.springframework.netfluxexample.service;

import guru.springframework.netfluxexample.domain.Movie;
import guru.springframework.netfluxexample.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    /**
     * Generate a stream of movie events for a given movie id
     *
     * @param movieId
     * @return
     */
    Flux<MovieEvent> events(String movieId);

    /**
     * Get a movie by id
     *
     * @param movieId
     * @return
     */
    Mono<Movie> getMoviebyId(String movieId);

    /**
     * Return the list of all movies
     *
     * @return
     */
    Flux<Movie> getAllMovies();
}
