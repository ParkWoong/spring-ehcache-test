package io.github.parkwoong.gateway.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import io.github.parkwoong.gateway.handler.CacheEvictor;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class Router {
    @Bean
    RouterFunction<ServerResponse> cacheEvict(CacheEvictor handler) {
        return route(GET("/cache/evict"), handler::handle);
    }
}
