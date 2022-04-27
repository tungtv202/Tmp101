package me.tungexplorer.study.redis;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;

import com.google.common.collect.ImmutableSet;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.redis.request.RedisRateLimiterFactory;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.resource.ClientResources;
import lombok.SneakyThrows;

public class RedisContainerDemo {

    GenericContainer container;

    @Test
    @SneakyThrows
    public void test() {

        container = new GenericContainer<>("redis:latest")
            .withCommand("redis-server --requirepass " + "password1")
            .withEnv("REDIS_PASSWORD", "tung");

        container.start();
        System.out.println(container.getMappedPort(6379));

//        RedisClient client = RedisClient.create("redis://:password1@localhost:" + container.getMappedPort(6379)+"/1");
        RedisClient client = RedisClient.create("redis://localhost:6379" + "/1");

        StatefulRedisConnection<String, String> connect = client.connect();
        RedisCommands<String, String> sync = connect.sync();

        RedisRateLimiterFactory factory = new RedisRateLimiterFactory(client);

        Set<RequestLimitRule> rules = ImmutableSet.of(RequestLimitRule.of(Duration.ofMinutes(100), 5)); // 50 request per minute, per key
        RequestRateLimiter requestRateLimiter = factory.getInstance(rules);
        boolean overLimit = requestRateLimiter.overLimitWhenIncremented("ip:127.0.0.2");

        while (true) {
            TimeUnit.SECONDS.sleep(2);

            Set<RequestLimitRule> rules2 = ImmutableSet.of(RequestLimitRule.of(Duration.ofMinutes(100), 1000)); // 50 request per minute, per key
            RequestRateLimiter requestRateLimiter2 = factory.getInstance(rules);
            boolean overLimit2 = requestRateLimiter2.overLimitWhenIncremented("ip:127.0.0.2");

            System.out.println(sync.get("TUNG"));
        }
    }

    @Test
    public void outdatedProblem() {
        RedisClient client = RedisClient.create("redis://localhost:6379/1");

        RedisRateLimiterFactory factory = new RedisRateLimiterFactory(client);

        RequestRateLimiter requestRateLimiter = factory.getInstance(ImmutableSet.of(RequestLimitRule.of(Duration.ofMinutes(100), 1)));

        final String key = "Key1";
        assertThat(requestRateLimiter.overLimitWhenIncremented(key)) // accept
            .isEqualTo(false);
        assertThat(requestRateLimiter.overLimitWhenIncremented(key))  // exceed
            .isEqualTo(true);

        RequestRateLimiter newRequestRateLimiter = factory.getInstance(ImmutableSet.of(RequestLimitRule.of(Duration.ofMinutes(100), 10)));
        assertThat(newRequestRateLimiter.overLimitWhenIncremented(key))  // should be acceptable, but result is exceeded
            .isEqualTo(false);
    }
}
