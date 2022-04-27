package me.tungexplorer.study.thread;

public class ThreadTwo extends Thread {
    NumberPrinter printer;

    public ThreadTwo(NumberPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
            printer.printNumber(2);
        }
    }
}
