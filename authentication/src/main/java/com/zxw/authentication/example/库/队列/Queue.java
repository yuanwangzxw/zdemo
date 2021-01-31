package com.zxw.authentication.example.库.队列;

import java.util.AbstractList;
import java.util.RandomAccess;

public class Queue<T> extends AbstractList<T> implements RandomAccess {

    private int capacity;
    private T[] queue;
    private int head;
    private int tail;

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
