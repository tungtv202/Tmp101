package me.tungexplorer.study.other;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerTest {

    @Test
    void testLogger() {
        var log1 = LoggerFactory.getLogger(LoggerTest.class);
        var log2 = LoggerFactory.getLogger(LoggerTest.class);
        var log3 = LoggerFactory.getLogger(LoggerTest.class);
        var log4 = LoggerFactory.getLogger(LoggerTest.class);
        assertThat(log1).isEqualTo(log2);
    }

    @SneakyThrows
    @Test
    void testCPU() {
        for (int i = 1; i <= 20; i++) {
            var temp = new Thread() {
                @Override
                public void run() {
                    print();
                }
            };
            temp.start();
        }

        while (true) {
            log.info("ok main");
            TimeUnit.SECONDS.sleep(2);
        }
    }


    @SneakyThrows
    private void print() {
        while (true) {
            log.info("ok ");
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
