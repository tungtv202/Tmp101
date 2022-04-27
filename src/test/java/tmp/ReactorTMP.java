package tmp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class ReactorTMP {

    @SneakyThrows
    @Test
    public void reactorProcessor() {
        List<String> elements = new ArrayList<>();
        Sinks.Many<String> listener = Sinks.many().multicast().directBestEffort();

        var asFlux = listener.asFlux()
            .doOnNext(e -> {
                System.out.println("subscribe " + e);
            })
            .subscribe();

        var temp = Flux.just("TUng", "Hau", "Ngoc", "Tran", "Quan")
            .subscribe(e -> {
                System.out.println("emitNext " + e);
                listener.emitNext(e, Sinks.EmitFailureHandler.FAIL_FAST);
            });

        while (true) {
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
