package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {

        private int rowSum;

        private int colSum;


        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
           sums[i] = getRowSums(matrix, i);
        }
        return sums;
    }

    public static Sums getRowSums(int[][] matrix, int index) {
        Sums sums = new Sums();
        for (int j = 0; j < matrix.length; j++) {
            sums.rowSum += matrix[index][j];
            sums.colSum += matrix[j][index];
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            map.put(i, getSumAsync(matrix, i));
        }
        for (Integer key : map.keySet()) {
            sums[key] = map.get(key).get();
        }
        return sums;
    }

    private static CompletableFuture<Sums> getSumAsync(int[][] matrix, int index) {
       return CompletableFuture.supplyAsync(() -> getRowSums(matrix, index));
    }

}
