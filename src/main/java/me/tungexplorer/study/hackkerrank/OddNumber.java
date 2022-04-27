package me.tungexplorer.study.hackkerrank;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class OddNumber {
    public static List<Integer> oddNumbers(int l, int r) {
        int l1 = Math.max(l, 0);
        int r2 = Math.max(r, 0);
        int start = Math.min(l1, r2);
        int end = Math.max(l1, r2);

        if (end == 0) return List.of();

        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 1) {
                results.add(i);
            }
        }
        System.out.println(results);
        return results;
    }

    ;

    public static void main(String[] args) {
        oddNumbers(2,5);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        int r = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> result = oddNumbers(l, r);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
                + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
