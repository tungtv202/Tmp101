package me.tungexplorer.study.reactor;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;

public class ReactorGroupTest {

    @Data
    @AllArgsConstructor
    static
    class Student {
        int id;
        int code;
    }

    @Test
    public void testGroup() {
        var flux1 = Flux.fromIterable(List.of(new Student(1, 1), new Student(2, 1), new Student(3, 1)))
            .delayElements(Duration.ofSeconds(2));
        var flux2 = Flux.fromIterable(List.of(new Student(3, 2), new Student(4, 2), new Student(5, 2), new Student(6, 2)))
            .delayElements(Duration.ofSeconds(2));

        var start = System.currentTimeMillis();
        var temp = Flux.merge(flux1, flux2)
            .groupBy(Student::getId)
            .flatMap(g -> g.reduce((a, b) -> b))
            .doOnNext(e -> System.out.println(e.getId() + ":" + e.getCode()))
//            .subscribeOn(Schedulers.elastic())
            .collectList()
            .block();

        System.out.println("Spend time: " + Duration.ofMillis(System.currentTimeMillis() - start).toSeconds());

    }
}
