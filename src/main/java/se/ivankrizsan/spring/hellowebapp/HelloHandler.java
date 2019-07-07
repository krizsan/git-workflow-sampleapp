package se.ivankrizsan.spring.hellowebapp;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

/**
 * Handles hello requests.
 *
 * @author Ivan Krizsan
 */
@Component
public class HelloHandler {

    /**
     * Handles the supplied request emitting a greeting.
     *
     * @param inServerRequest Request to handle.
     * @return Response mono.
     */
    public Mono<ServerResponse> hello(final ServerRequest inServerRequest) {
        final LocalTime theLocalTime = LocalTime.now();

        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(
                BodyInserters
                    .fromObject(
                        "Hello World, the time is now: " + theLocalTime));
    }
}