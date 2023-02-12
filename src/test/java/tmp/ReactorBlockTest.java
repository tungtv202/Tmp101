package tmp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ReactorBlockTest {


    @Test
    void test() throws InterruptedException {
        var temp = Flux.fromIterable(IntStream.range(0, 10).boxed().collect(Collectors.toList()))
            .map(this::abc)
            .subscribeOn(Schedulers.newBoundedElastic(3, 10, "Tung"))
            .collectList()
            .block();
        System.out.println(temp);
    }

    private String abc(int value) {
        log.info(value + "");
  /*      try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        log.info("done " + value);
        return "value " + value;
    }
}
