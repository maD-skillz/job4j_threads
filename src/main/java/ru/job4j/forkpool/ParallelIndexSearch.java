package ru.job4j.forkpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch extends RecursiveTask<Integer> {

    private final int[] array;

    private final int from;

    private final   int to;

    private final int indexToSearch;


    public ParallelIndexSearch(int[] array, int from, int to, int indexToSearch) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.indexToSearch = indexToSearch;
    }

    public Integer findIndex(int[] array, int indexToSearch) {
        for (int i = 0; i < array.length; i++) {
                if (array[i] == indexToSearch) {
                    return array[i];
                }

        }
        return -1;
    }

    @Override
    protected Integer compute() {
        int result = -1;
        for (int i = from; i < to; i++) {
            if (array[i] == indexToSearch) {
                result = array[i];
            }
        }
        return result;
    }

    public Integer search(int[] array) {
        if (array.length <= 10) {
            return findIndex(array, indexToSearch);
        } else {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            return forkJoinPool.invoke(
                    new ParallelIndexSearch(array, 0, array.length, indexToSearch));
        }
    }
}
