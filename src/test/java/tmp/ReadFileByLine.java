package tmp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadFileByLine {

    public static HashMap<String, Set<String>> depen = new HashMap<>();

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                "/tmp/2"));
            String line = reader.readLine();
            while (line != null) {

                var head = getHead(line);
                var info = getInfo(line);

                if (depen.containsKey(head)) {
                    depen.get(head).add(info);
                } else {
                    HashSet<String> tails = new HashSet<>();
                    tails.add(info);
                    depen.put(head, tails);
                }

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AtomicInteger counter = new AtomicInteger();
        for (Map.Entry<String, Set<String>> stringSetEntry : depen.entrySet()) {
            if (stringSetEntry.getValue().size() > 1) {
                counter.incrementAndGet();
                var value = stringSetEntry.getValue();
                System.out.println(stringSetEntry);
            }
        }
        System.out.println(counter);
    }

    public static String getTail(String s) {
        try {
            return s.substring(s.indexOf("jar:"))
                .replaceAll(":compile", "")
                .replaceAll(":test", "")
                .replaceAll(":provided", "")
                .replaceAll(":runtime", "")

                ;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getHead(String s) {
        try {
            var head = s.replaceAll(getTail(s), "");
            var temp = head.substring(head.indexOf("\\- ")).replaceAll("\\- ", "");
            if (temp.startsWith("\\")) {
                return temp.substring(1);
            }
            return temp;
        } catch (Exception e) {
            return "";
        }
    }

    public static final String getInfo(String line) {
        try {
            var temp = line.substring(line.indexOf("\\- ")).replaceAll("\\- ", "");
            if (temp.startsWith("\\")) {
                return temp.substring(1);
            }
            return temp;
        } catch (Exception e) {
            return "";
        }
    }
}
