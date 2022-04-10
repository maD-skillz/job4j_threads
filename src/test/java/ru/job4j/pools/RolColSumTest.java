package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.job4j.pools.RolColSum.sum;

public class RolColSumTest {

    @Test
    public void colSumTest() {
        int[][] array = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = sum(array);
        assertThat(sums[0].getColSum(), is(12));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[2].getColSum(), is(18));
    }

    @Test
    public void rowSumTest() {
        int[][] array = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = sum(array);
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[2].getRowSum(), is(24));
    }

}