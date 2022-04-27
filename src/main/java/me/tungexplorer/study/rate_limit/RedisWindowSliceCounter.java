package me.tungexplorer.study.rate_limit;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableSet;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.redis.request.RedisRateLimiterFactory;
import io.lettuce.core.RedisClient;
import lombok.SneakyThrows;

public class RedisWindowSliceCounter {

    @SneakyThrows
    @Test
    public void test() {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        RedisRateLimiterFactory factory = new RedisRateLimiterFactory(client);

        Set<RequestLimitRule> rules = ImmutableSet.of(
            RequestLimitRule.of(Duration.ofSeconds(5), 30),
            RequestLimitRule.of(Duration.ofMinutes(1), 100).withPrecision(Duration.ofSeconds(20)));


        RequestRateLimiter requestRateLimiter = factory.getInstance(rules);
        boolean overLimit2 = requestRateLimiter.overLimitWhenIncremented("ip:127.0.0.2", 30);
        boolean overLimit3 = requestRateLimiter.overLimitWhenIncremented("ip:127.0.0.2", 33);

        while (true) {
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
