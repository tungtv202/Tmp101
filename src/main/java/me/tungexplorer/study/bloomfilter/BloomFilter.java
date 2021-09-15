package me.tungexplorer.study.bloomfilter;

import com.google.common.base.Preconditions;

public interface BloomFilter {

    void add(Object object);

    boolean mightContain(Object object);
}

class MyBloomFilter implements BloomFilter {

    private final int[] array;

    MyBloomFilter(int size) {
        this.array = new int[size];
    }

    @Override
    public void add(Object object) {
        int hashPosition = hashObjectAsInt(object);
        array[hashPosition] = 1;
    }

    @Override
    public boolean mightContain(Object object) {
        int hashPosition = hashObjectAsInt(object);
        return array[hashPosition] == 1;
    }

    private int hashObjectAsInt(Object object) {
        int result = object.hashCode();
        // todo. result = Hash object with something algorithm
        Preconditions.checkArgument(0 <= result && result < array.length - 1);
        return result;
    }
}

