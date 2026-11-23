package io.github.parkwoong.gateway.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.util.Iterator;

import javax.cache.Cache.Entry;
import javax.cache.CacheManager;

@Component
@RequiredArgsConstructor
@Slf4j
public class CacheEvictor {

    private final CacheManager cacheManager;

    public Mono<ServerResponse> handle(ServerRequest serverRequest) {

        return Mono.justOrEmpty(serverRequest.queryParam("cacheName"))
                .mapNotNull(cacheManager::getCache)
                .map(
                        cache -> {
                            log.info("CacheManager has cache");

                            final String key = serverRequest.queryParam("key").orElse("");
                            final Iterator<Entry<Object, Object>> iterator = cache.iterator();

                            boolean updated = false;
                            while (iterator.hasNext()) {

                                final Entry<Object, Object> next = iterator.next();

                                if (next.getKey().toString().endsWith(key)) {
                                    System.out.println("cache eviction: " + next.getKey());
                                    iterator.remove();
                                    updated = true;
                                }
                            }
                            return updated;
                        })
                .defaultIfEmpty(Boolean.FALSE)
                .flatMap(ServerResponse.ok()::bodyValue);
    }
}
