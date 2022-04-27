package tmp;

import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Comparators;

import lombok.SneakyThrows;

public class PriorityQueue01 {
    @SneakyThrows
    public static void main(String[] args) {

        PriorityQueue<Integer> numbers = new PriorityQueue<>((i1, i2) -> {
            if (i1 < 10 && i2 < 10) {
                return i1 < i2 ? -1 : 1;
            }
            return -1;
        });


        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(11);
        numbers.add(12);
        numbers.add(90);

        while(true){
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(new Date());
        }

    }
}
