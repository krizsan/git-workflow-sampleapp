package se.ivankrizsan.spring.hellowebapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Configures routing of requests.
 *
 * @author Ivan Krizsan
 */
@Configuration
public class HelloRouter {

    /**
     * Bean defining the route from the /hello path to the
     * supplied {@code HelloHandler}.
     *
     * @param inHelloHandler Hello handler to route requests to.
     * @return Route function.
     */
    @Bean
    public RouterFunction<ServerResponse> routeHello(final HelloHandler inHelloHandler) {
        return RouterFunctions
            .route(
                RequestPredicates.GET("/hello")
                    .and(
                        RequestPredicates
                            .accept(MediaType.ALL)), inHelloHandler::hello);
    }
}