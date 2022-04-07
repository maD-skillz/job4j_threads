package ru.job4j.forkpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch extends RecursiveTask<Integer> {

    private final int[] array;

    private final int start;

    private final  int end;

    private final int element;


    public ParallelIndexSearch(int[] array, int start, int end, int element) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.element = element;
    }

    public Integer findIndex(int[] array, Integer element) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                result = array[i];
            }
        }
        return result;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= 10) {
            return findIndex(array, element);
        }
        int mid = (start + end) / 2;
        ParallelIndexSearch left = new ParallelIndexSearch(array, start, mid, element);
        ParallelIndexSearch right = new ParallelIndexSearch(array, mid + 1, end, element);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    public static Integer search(int[] array, int element) {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            return forkJoinPool.invoke(
                    new ParallelIndexSearch(array, 0, array.length, element));
        }
    }
