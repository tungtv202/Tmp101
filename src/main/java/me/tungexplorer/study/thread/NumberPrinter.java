package me.tungexplorer.study.thread;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberPrinter {
    private int nextThreadId;
    private int maxThreadId;

    public int getMaxThreadId() {
        return maxThreadId;
    }

    public void setMaxThreadId(int maxThreadId) {
        this.maxThreadId = maxThreadId;
    }

    public NumberPrinter(int maxThreadId) {
        this.nextThreadId = 1;
        this.maxThreadId = maxThreadId;
    }

    static int i = 1;

    public synchronized void printNumber(int number) {
        try {
            i++;
            if (number != nextThreadId) {
                log.info("Thread " + number + " goes to wait state");
                wait();
                log.info("finish await");
            } else {
                log.info(number + "");
                if (number == this.maxThreadId) {
                    resetPrintingOrder();
                    System.out.println();
                } else {
                    nextThreadId++;
                }
                log.info("Next expected threadId: " + nextThreadId);
//                this.notifyAll();
            }
            if (i > 20) {
                throw new RuntimeException("crash");
            }
        }
        catch (InterruptedException ie) {

        }
    }

    private void resetPrintingOrder() {
        this.nextThreadId = 1;
    }

    public static void main(String[] args) {


    }

    private int count = 0;

    public synchronized void doRecur() {
        if (count == 3)
            return;
        count++;
        doRecur();
    }


}
