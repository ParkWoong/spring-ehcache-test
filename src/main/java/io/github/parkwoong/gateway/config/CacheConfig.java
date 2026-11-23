package io.github.parkwoong.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

@Configuration
public class CacheConfig {

    private final ClassPathResource classPathResource = new ClassPathResource("ehcache.xml");

    @Bean
    public CacheManager cacheManager() throws IOException {
        final CachingProvider cachingProvider = Caching.getCachingProvider();
        final CacheManager cacheManager =
                cachingProvider.getCacheManager(classPathResource.getURI(), null);

        return cacheManager;
    }
}
