# Ehcache with JPA / JPA와 Ehcache

[ENG](#eng) | [KR](#kr)

## ENG

### Project structure

```text
pom.xml
src/main/java/io/github/parkwoong/gateway/Application.java
src/main/java/io/github/parkwoong/gateway/backend/Backend.java
src/main/java/io/github/parkwoong/gateway/backend/BackendRepository.java
src/main/java/io/github/parkwoong/gateway/config/CacheConfig.java
src/main/java/io/github/parkwoong/gateway/config/HashMapConverter.java
src/main/java/io/github/parkwoong/gateway/handler/CacheEvictor.java
src/main/java/io/github/parkwoong/gateway/router/Router.java
src/main/resources/application.properties
src/main/resources/ehcache.xml
src/test/java/io/github/parkwoong/gateway/CacheTest.java
```

### Code explanation

CacheConfig creates a JCache CacheManager from ehcache.xml. Backend has Hibernate READ_ONLY
cache annotations. CacheEvictor exposes a local cache eviction route. CacheTest saves and reads
a sample entity and checks CacheManager startup.

### Run and test

Use JDK 8 and Maven 3.6.3+.

```sh
java -version
mvn clean verify
mvn spring-boot:run
```

The app listens on localhost:8080. The test checks startup and entity access; it does not
measure cache hit rates.

#### Postman API

##### Evict from a missing cache

**Request**

- Method: GET
- URL: http://localhost:8080/cache/evict?cacheName=missing&key=sample
- Body: none

**Response**

- Status: 200 OK

~~~json
false
~~~

**Note:** The existing route returns true only when it removes a matching entry.

## KR

### 프로젝트 구조

```text
pom.xml
src/main/java/io/github/parkwoong/gateway/Application.java
src/main/java/io/github/parkwoong/gateway/backend/Backend.java
src/main/java/io/github/parkwoong/gateway/backend/BackendRepository.java
src/main/java/io/github/parkwoong/gateway/config/CacheConfig.java
src/main/java/io/github/parkwoong/gateway/config/HashMapConverter.java
src/main/java/io/github/parkwoong/gateway/handler/CacheEvictor.java
src/main/java/io/github/parkwoong/gateway/router/Router.java
src/main/resources/application.properties
src/main/resources/ehcache.xml
src/test/java/io/github/parkwoong/gateway/CacheTest.java
```

### 코드 설명

CacheConfig가 ehcache.xml로 JCache CacheManager를 생성한다. Backend에는
Hibernate READ_ONLY 캐시 애너테이션이 있다. CacheEvictor는 로컬 캐시 삭제 경로를 제공한다.
CacheTest는 샘플 엔티티 저장·조회와 CacheManager 시작을 확인한다.

### 실행과 테스트

JDK 8, Maven 3.6.3 이상 사용. 저장소 루트에서 실행.

```sh
java -version
mvn clean verify
mvn spring-boot:run
```

앱은 localhost:8080에서 실행된다. 테스트는 시작과 엔티티 접근을 확인하며 캐시 적중률은 측정하지 않는다.

#### Postman API

##### 없는 캐시에서 삭제 시도

**요청**

- 메서드: GET
- URL: http://localhost:8080/cache/evict?cacheName=missing&key=sample
- 본문: 없음

**응답**

- 상태: 200 OK

~~~json
false
~~~

**참고:** 일치하는 항목을 실제로 삭제한 경우에만 true.
