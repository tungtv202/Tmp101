package me.tungexplorer.study.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public class ThreadStaticMethod {

    @SneakyThrows
    public static void staticMethod() {
        System.out.println(Thread.currentThread().getName() + " - " + new Date());
        TimeUnit.SECONDS.sleep(5);
    }


    public static void main(String[] args) {
        for (int i = 1; i < 5; i++) {
            new Thread(ThreadStaticMethod::staticMethod).start();
        }
    }
}
