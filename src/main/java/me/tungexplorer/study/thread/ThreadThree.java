package me.tungexplorer.study.thread;

public class ThreadThree extends Thread {
    NumberPrinter printer;

    public ThreadThree(NumberPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
            printer.printNumber(3);
        }
    }
}
