package me.tungexplorer.study.bloomfilter;

import java.util.Arrays;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        int[] temp = new int[10];
        temp[1] = 1;
        temp[0] = 3;

        Arrays.stream(temp).asLongStream()
            .forEach(e-> System.out.println(e));

    }
}
