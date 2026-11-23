package io.github.parkwoong.gateway;

import static org.junit.jupiter.api.Assertions.*;

import io.github.parkwoong.gateway.backend.Backend;
import io.github.parkwoong.gateway.backend.BackendRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.cache.CacheManager;

@SpringBootTest
class CacheTest {
    @Autowired BackendRepository repository;
    @Autowired CacheManager manager;

    @Test
    void loadsReadOnlyEntityAndCacheManager() {
        Backend row = Backend.with("http://localhost:9090");
        row.setPattern("/sample");
        repository.saveAndFlush(row);
        assertEquals("/sample", repository.findById("/sample").get().getPattern());
        assertNotNull(manager);
    }
}
