package me.tungexplorer.study.reactor;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ReactorBlockTest {

    public static void main(String[] args) throws InterruptedException {
        Scheduler schedulerA = Schedulers.newParallel("scheduler-a", 4);
        Scheduler schedulerB = Schedulers.newParallel("scheduler-b", 4);


        Flux.range(1, 10)
            .map(i -> {
                System.out.println(String.format("First map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                return i;
            })
            .subscribeOn(schedulerA)
            .map(i -> {
                System.out.println(String.format("Second map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                return i;
            })
            .publishOn(schedulerB)
            .map(i -> {
                System.out.println(String.format("Third map - (%s), Thread: %s", i, Thread.currentThread().getName()));
                /*log.info(i + "");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("done " + i);*/
                return i;

            })
            .blockLast();
    }


    private static String abc(int value) {
        log.info(value + "");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("done " + value);
        return "value " + value;
    }
}
