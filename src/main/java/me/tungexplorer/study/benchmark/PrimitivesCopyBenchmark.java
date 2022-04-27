package me.tungexplorer.study.benchmark;
import java.io.IOException;

import org.openjdk.jmh.annotations.*;

public class PrimitivesCopyBenchmark {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void init() {
        System.out.println(123);
    }
}
