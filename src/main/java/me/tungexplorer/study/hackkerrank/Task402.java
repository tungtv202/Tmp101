package me.tungexplorer.study.hackkerrank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task402 {

    public static int getSpecialSubstring(String s, int k, String charValue) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Set<String> normals = new HashSet<>();

        for (int i = 0; i < charValue.length(); i++) {
            String c = String.valueOf(charValue.charAt(i));
            if (c.equals("0")) {
                normals.add(String.valueOf(alphabet.charAt(i)));
            }
        }
        int counter = 0;
        int normalCharactersCounter = 0;

        for (int i = 0; i < s.length(); i++) {
            counter++;
            if (normals.contains(String.valueOf(s.charAt(i)))) {
                normalCharactersCounter++;
                if (normalCharactersCounter > k) {
                    counter = 1;
                    normalCharactersCounter = 1;
                }
            }
        }

        return counter;
    }

    public static void main(String[] args) {

        System.out.println(getSpecialSubstring("giraffe",2, "01111001111111111011111111"));
    }
}
