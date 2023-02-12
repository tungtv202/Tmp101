package me.tungexplorer.study.thread;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteFutureT1 {

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            sleep(4000);
            return "Hello-Educative";
        });

        int count = 0;
        int waitTimeCounter = new Random().nextInt(3);

        while(!completableFuture.isDone()) {
            sleep(2000);
            System.out.println("Waiting...");
            count++;
            if(count > waitTimeCounter) completableFuture.cancel(true);
        }

        if(!completableFuture.isCancelled()){
            String result = completableFuture.get();
            System.out.println("Retrieved result from the task - " + result);
        }else {
            System.out.println("The future was cancelled");
        }
    }
}
