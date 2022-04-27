package me.tungexplorer.study.thread;

public class ThreadOne extends Thread {
    NumberPrinter printer;

    public ThreadOne(NumberPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
            printer.printNumber(1);
        }
    }
}
