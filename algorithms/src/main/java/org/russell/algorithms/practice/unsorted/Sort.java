package org.russell.algorithms.practice.unsorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Sort {

    public static void main(String[] args) {
        
        final int[] array = {3, 7, 8, 5, 2, 1, 9, 5, 4};
        quickSort(array);
        
        System.out.println(Arrays.toString(array));
        
        final Map<Integer, Integer> map = new HashMap<>();
        
    }
    
    public static void quickSort(final int[] array) {
        quickSort(array, 0, array.length-1);
    }
    
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int p = partition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        final int pivot = array[high];
        int i = low;
        int j = low;
        while (j < high) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
            swap(array, i, high);
            j++;
        }
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static List<Integer> lameQuickSort(final List<Integer> array) {
        if (array.size() < 2) {
            return array;
        }
        
        final int pivot = array.size() / 2;
        
        final List<Integer> result = new ArrayList<>();
        final List<Integer> less = new ArrayList<>();
        final List<Integer> greater = new ArrayList<>();
        
        for (int i = 0; i < array.size(); i++) {
            if (i == pivot) {
                continue;
            }
            if (array.get(i) < array.get(pivot)) {
                less.add(array.get(i));
            } else {
                greater.add(array.get(i));
            }
        }
        
        result.addAll(lameQuickSort(less));
        result.add(array.get(pivot));
        result.addAll(lameQuickSort(greater));
        return result;
    }
    
}
