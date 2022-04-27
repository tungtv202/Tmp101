package me.tungexplorer.study.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoCloseableDemo {

    @AllArgsConstructor
    static class CustomCloseable implements AutoCloseable {
        @Getter
        @Setter
        private String value;

        @Override
        public void close() throws Exception {
            System.out.println("Closed : " + value);;
        }
    }

    public static void main(String[] args) throws Exception {
        try (CustomCloseable test = new CustomCloseable("Tung")) {
            System.out.println(test.getValue());
        }
    }
}
