package me.tungexplorer.study;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;

public class Tmp1 {

    static Stream<String> sources = Stream.of("TUNG", "Hau");

    public static void main(String[] args) throws InterruptedException {
        System.getProperties().setProperty("com.sun.management.jmxremote.password.file","/home/tungtv/workplace/jmxremote.password2");
        List<TmpObject1> list = new ArrayList<>();
        for (int i = 0; i < 1000_000; i++) {
            TmpObject1 temp = new TmpObject1(i + "");
            list.add(temp);
            System.out.println(list.size());
            Thread.sleep(1000);
            if (i>60) {
                System.getProperties().setProperty("com.sun.management.jmxremote.password.file","/home/tungtv/workplace/jmxremote.password2");
//                System.setProperty("com.sun.management.jmxremote.password.file", "/home/tungtv/workplace/jmxremote.password2");
            }
        }
    }


    static Publisher<Integer> publisher1() {
        return Mono.fromCallable(() -> new Random().nextInt())
            .doOnNext(e -> System.out.println(e))
            .then(Mono.empty());
    }
}
