package me.tungexplorer.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceManager {
    public static int processors = Runtime.getRuntime().availableProcessors();
    public static ExecutorService executorService = Executors.newFixedThreadPool(processors);


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        sendBackendTrackingData();
        System.out.println("123");
        System.out.println(45);
    }

    public static void sendBackendTrackingData() {
        try {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(4556);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            // ignored
        }
    }
}