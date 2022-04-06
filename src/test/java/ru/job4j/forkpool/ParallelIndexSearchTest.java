package ru.job4j.forkpool;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParallelIndexSearchTest {

    @Test
    public void testWhenNumberMoreThan10() {
        int[] arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ParallelIndexSearch parallelIndexSearch =
                new ParallelIndexSearch(arr, 0, arr.length, 13);
        assertThat(parallelIndexSearch.search(arr), is(13));
    }

    @Test
    public void testWhenNumberLessThan10() {
        int[] arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ParallelIndexSearch parallelIndexSearch =
                new ParallelIndexSearch(arr, 0, arr.length, 9);
        assertThat(parallelIndexSearch.search(arr), is(9));
    }

    @Test
    public void testWhenNumberIsFalse() {
        int[] arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ParallelIndexSearch parallelIndexSearch =
                new ParallelIndexSearch(arr, 0, arr.length, 14);
        assertThat(parallelIndexSearch.search(arr), is(-1));
    }

}