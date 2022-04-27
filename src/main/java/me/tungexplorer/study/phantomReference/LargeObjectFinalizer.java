package me.tungexplorer.study.phantomReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class LargeObjectFinalizer extends PhantomReference<Object> {
    Object target;

    public LargeObjectFinalizer(Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
        this.target = referent;
    }

    public void finalizeResources() {
        System.out.println("clearing ...");
    }

}

class Test101 {

    @Test
    void test() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<LargeObjectFinalizer> references = new ArrayList<>();
        List<Object> largeObjects = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Object largeObject = new Object();
            largeObjects.add(largeObject);
            references.add(new LargeObjectFinalizer(largeObject, referenceQueue));
        }

        largeObjects = null;
        System.gc();
        for (PhantomReference<Object> reference : references) {
            System.out.println(reference.isEnqueued());
        }

        Reference<?> referenceFromQueue;
        while ((referenceFromQueue = referenceQueue.poll()) != null) {
            ((LargeObjectFinalizer)referenceFromQueue).finalizeResources();
            referenceFromQueue.clear();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Tung1 t = new Tung1("tung");
        t = null;
        System.gc();
        while (true) {
            TimeUnit.SECONDS.sleep(123);
        }

    }
}

class Tung1 {
    String name;

    public Tung1(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("tung finalize");
    }
}