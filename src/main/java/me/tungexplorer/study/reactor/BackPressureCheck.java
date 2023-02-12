package me.tungexplorer.study.reactor;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;

public class BackPressureCheck {

    static AtomicInteger hitedCounter = new AtomicInteger();

    public static void main(String[] args) {
        int windowSize = 20;
        Duration duration = Duration.ofMillis(2000);
        AtomicInteger generator2Used = new AtomicInteger();

        AtomicReference<Instant> clock = new AtomicReference<>(Instant.now());

        Faker faker = new Faker();
        Flux.fromStream(IntStream.range(1, 110097).boxed())
            .concatMap(e -> {
                Duration duration1 = Duration.ofMillis(faker.number().numberBetween(10, 30));
                return Mono.delay(duration1)
                    .doOnNext(e1 -> System.out.println("Counter " + generator2Used.incrementAndGet()))
                    .thenReturn(e);
            })
            .window(windowSize)
            .delayElements(duration)
            .doOnNext(e -> {
                var now = Instant.now();
                System.out.println("__________  Time between : " + Duration.ofMillis(clock.get().toEpochMilli() - now.toEpochMilli()).toSeconds());
                clock.set(now);
            })
            .concatMap(window -> window.flatMap(i -> blockCall(i).subscribeOn(Schedulers.newBoundedElastic(10,20, "TungBounded"))
                .doOnNext(e -> generator2Used.decrementAndGet()), Queues.SMALL_BUFFER_SIZE))
            .last()
            .block();
    }

    private static Mono<String> blockCall(int i) {
        return Mono.fromCallable(() -> {
            try {
                Thread.sleep(5000 + hitedCounter.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Hited %s, Thread = %s%n", hitedCounter.incrementAndGet(), Thread.currentThread().getName());
            return String.valueOf(i);
        });

    }
}
