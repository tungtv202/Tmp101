package me.tungexplorer.study.thread;

public class NotifyAllTest {
    public static void main(String[] args) {
        NumberPrinter printer = new NumberPrinter(3);
        ThreadOne one = new ThreadOne(printer);
        ThreadTwo two = new ThreadTwo(printer);
        ThreadThree three = new ThreadThree(printer);

        one.start();
        two.start();
        three.start();
    }
}
