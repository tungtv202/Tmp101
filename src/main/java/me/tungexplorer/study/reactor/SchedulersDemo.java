package me.tungexplorer.study.reactor;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

// https://www.youtube.com/watch?v=J86tQDnUOw4&ab_channel=MuhammedShakir
public class SchedulersDemo {

    public static void main(String[] args) throws InterruptedException {

//        ExecutorService service = Executors.newFixedThreadPool(5);

        Scheduler scheduler = Schedulers.elastic();

        int numberOfTasks = 30000;
        System.out.println("HeapSize start totalMemory " + bytesAsMB(Runtime.getRuntime().totalMemory()));
        System.out.println("HeapSize start maxMemory " + bytesAsMB(Runtime.getRuntime().maxMemory()));
        long freeMemory1 = Runtime.getRuntime().freeMemory();
        System.out.println("HeapSize start freeMemory " + bytesAsMB(Runtime.getRuntime().freeMemory()));
        CountDownLatch latch = new CountDownLatch(numberOfTasks);
        long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfTasks; i++) {
            final int j = i;
            scheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(j + " task is executed in : " + Thread.currentThread().getName() + " : " + Thread.currentThread().isDaemon());
                    latch.countDown();
                }
            });
        }

        System.out.println("HeapSize end totalMemory " + Runtime.getRuntime().totalMemory());
        System.out.println("HeapSize end maxMemory " + Runtime.getRuntime().maxMemory());
        System.out.println("HeapSize end freeMemory " + Runtime.getRuntime().freeMemory());
        long freeMemory2 = Runtime.getRuntime().freeMemory();
        System.out.println("resource for 1 thread = " + (freeMemory1 - freeMemory2) / numberOfTasks);

        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("Tasks done in " + (end - start) + " ms");
//        service.shutdown();
    }

    private static long bytesAsKB(long byteCount) {
        return byteCount / 1024;
    }

    private static long bytesAsMB(long byteCount) {
        return byteCount / 1024 / 1024;
    }

    @SneakyThrows
    @Test
    void newThread() {
        System.out.println("HeapSize start maxMemory " + formatSize(Runtime.getRuntime().maxMemory()));

        int numberOfTasks = 2000000;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        for (int i = 1; i <= numberOfTasks; i++) {
            int finalI = i;
            try {
                new Thread(() -> {
//                System.out.println(finalI + " task is start in : " + Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(finalI + " task is finish in : " + Thread.currentThread().getName());
                    latch.countDown();
                }).start();
            } catch (Throwable e) {
                System.out.println("Error at " + finalI);
                break;
            } finally {
                System.out.println("Tung " + finalI);
            }
        }
        latch.await();
    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }
}
