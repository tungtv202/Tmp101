package me.tungexplorer.study;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.base.Strings;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamTest {

    @Test
    public void testStreamIterator() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
        var list2 = list.stream().parallel()
            .map(this::function1)
            .map(this::function2)
            .collect(Collectors.toList());
        log.info("{}", list2);
    }

    @SneakyThrows
    private Integer function2(Integer e2) {
        TimeUnit.SECONDS.sleep(2);
        log.info("function2 {}", e2);

        return e2 - 1;
    }

    private Integer function1(Integer e) {
        log.info("function1 {}", e);
        return e * 2;
    }

    public static void main(String[] args) {

    }

    void temp(String str) throws IOException {
        if (Strings.isNullOrEmpty(str)) {
            throw new RuntimeException();
        }
        throw new IOException("tung");
    }
}
