package ru.job4j.synch;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    @GuardedBy("list")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = copy(list);
    }

    public void add(T value) {
        list.add(value);
    }

    public T get(int index) {
        T res = null;
        for (T i : list) {
            if (i.equals(index)) {
                res = i;
            }
        }
        return res;
    }

    public synchronized Iterator<T> iterator() {
        return copy(this.list).listIterator();

    }

    public synchronized List<T> copy(List<T> list) {
       return new ArrayList<>(list);
    }

}