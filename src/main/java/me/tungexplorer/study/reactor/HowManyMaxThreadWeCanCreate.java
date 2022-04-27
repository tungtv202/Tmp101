package me.tungexplorer.study.reactor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HowManyMaxThreadWeCanCreate {

    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 30000;
        var usage1 = Runtime.getRuntime().freeMemory();
        System.out.println("HeapSize start maxMemory " + formatSize(Runtime.getRuntime().maxMemory()));
        CountDownLatch latch = new CountDownLatch(numberOfTasks);

        for (int i = 1; i <= numberOfTasks; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(finalI + " task is started in : " + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(finalI + " task is finished in : " + Thread.currentThread().getName());
                latch.countDown();
            }).start();
        }
        System.out.println(formatSize((usage1-Runtime.getRuntime().freeMemory())/numberOfTasks));

        latch.await();
    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double) v / (1L << (z * 10)), " KMGTPE".charAt(z));
    }
}
