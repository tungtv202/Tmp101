package me.tungexplorer.study.hackkerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Task301 {

    public static List<String> funWithAnagrams(List<String> text) {
        List<String> result = new ArrayList<String>();
        if (text.isEmpty()) return result;
        Set<String> exited = new HashSet<>();
        for (String s : text) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = Arrays.toString(chars);
            if (!exited.contains(sorted)) {
                exited.add(sorted);
                result.add(s);
            }
        }
        Collections.sort(result);
        return result;
    }


    public static void main(String[] args) {
        List<String> strings = funWithAnagrams(List.of("frame","code", "doce"));
        System.out.println(strings);
    }


}
