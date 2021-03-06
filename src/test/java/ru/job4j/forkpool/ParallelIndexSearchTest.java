package ru.job4j.forkpool;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParallelIndexSearchTest {

    @Test
    public void testWhenNumberMoreThan10() {
        Integer[] arr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        assertThat(ParallelIndexSearch.search(arr, 7), is(7));
    }

    @Test
    public void testWhenNumberLessThan10() {
        Integer[] arr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(ParallelIndexSearch.search(arr, 4), is(4));
    }

    @Test
    public void testWhenNumberIsFalse() {
        Integer[] arr = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        assertThat(ParallelIndexSearch.search(arr, 16), is(-1));
    }

}