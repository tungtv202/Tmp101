package me.tungexplorer.study.reactor;

import static reactor.core.scheduler.Schedulers.DEFAULT_BOUNDED_ELASTIC_QUEUESIZE;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Tmp1 {
    static AtomicInteger hitedCounter = new AtomicInteger();
    static int initialResponseTime = 2;

    public static void main(String[] args) {
        int windowSize = 20;
        Duration duration = Duration.ofMillis(1000);
        AtomicInteger generator2Used = new AtomicInteger();
        AtomicInteger backoffExponentialCounter = new AtomicInteger();

        Instant start = Instant.now();

        Mono.fromRunnable(() -> System.out.println("HIT Counter " + generator2Used.get()))
            .delayElement(Duration.ofSeconds(3))
            .repeat(100000)
            .subscribe();

        Faker faker = new Faker();
        Flux.fromStream(IntStream.range(1, 110097).boxed())
            .concatMap(e -> {
                Duration duration1 = Duration.ofMillis(faker.number().numberBetween(10, 100));
                return Mono.delay(duration1)
                    .doOnNext(e1 -> System.out.println("Counter " + generator2Used.incrementAndGet()))
                    .thenReturn(e);
            })
            .window(windowSize)
            .delayElements(duration)
            .doOnNext(e -> System.out.println("__________  Time between : " + Duration.ofMillis(start.toEpochMilli() - Instant.now().toEpochMilli())))
            .concatMap(window -> window.flatMap(i -> blockCall(i, backoffExponentialCounter.incrementAndGet())
//                .timeout(Duration.ofSeconds(15))
                .doOnNext(e -> generator2Used.decrementAndGet()), 256)
                .onErrorReturn("empty")
            )
            .last()
            .block();
    }

    private static Mono<String> blockCall(int i, int backoffExponentialCounter) {
        return Mono.fromCallable(() -> {
                //                System.out.println("Sleep time " + Math.pow(initialResponseTime, backoffExponentialCounter));
                try {
                    Thread.sleep((long) Math.pow(initialResponseTime, backoffExponentialCounter));
                //                Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("Hited %s, Thread = %s. Rspamd response time: %d %n", hitedCounter.incrementAndGet(), Thread.currentThread().getName(), 3000 + backoffExponentialCounter);
                return String.valueOf(i);
            })
            .subscribeOn(Schedulers.newBoundedElastic(60, DEFAULT_BOUNDED_ELASTIC_QUEUESIZE,
                "blocking-call-wrapper", 60, true));
    }

}
