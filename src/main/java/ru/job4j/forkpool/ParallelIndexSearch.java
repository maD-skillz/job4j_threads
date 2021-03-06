package ru.job4j.forkpool;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;

    private final int start;

    private final int end;

    private final T element;


    public ParallelIndexSearch(T[] array, int start, int end, T element) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.element = element;
    }

    public Integer findIndex() {
        for (int i = start; i <= end; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= 10) {
           return findIndex();
        }
        int mid = (start + end) / 2;
        ParallelIndexSearch<T> left = new ParallelIndexSearch<>(array, start, mid, element);
        ParallelIndexSearch<T> right = new ParallelIndexSearch<>(array, mid + 1, end, element);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    public static Integer search(Integer[] array, Integer element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(
                new ParallelIndexSearch<>(array, 0, array.length - 1, element));
    }

}