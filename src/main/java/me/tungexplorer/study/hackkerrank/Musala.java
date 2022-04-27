package me.tungexplorer.study.hackkerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


class Result {

    /*
     * Complete the 'newPassword' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static String newPassword(String a, String b) {
        if (a.isBlank()) {
            return getLowerCaseWithNoneSpace(b);
        }
        if (b.isBlank()) {
            return getLowerCaseWithNoneSpace(a);
        }

        String a1 = getLowerCaseWithNoneSpace(a);
        String b1 = getLowerCaseWithNoneSpace(b);
        int lengthOfMin = Math.min(a1.length(), b1.length());
        boolean isA1Longer = a1.length() > b1.length();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lengthOfMin; i++) {
            result.append(a1.charAt(i));
            result.append(b1.charAt(i));
        }

        if (isA1Longer) {
            result.append(a1.substring(lengthOfMin));
        } else if (b1.length() > a1.length()) {
            result.append(b1.substring(lengthOfMin));
        }
        return result.toString();
    }

    ;

    private static String getLowerCaseWithNoneSpace(String input) {
        if (input.isBlank()) return "";
        return input.replaceAll("[^a-z]", "").toLowerCase();
    }

    public static void main(String[] args) {
        System.out.println(newPassword("cat", "rabbit"));
    }

}

public class Musala {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        String result = Result.newPassword(a, b);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}