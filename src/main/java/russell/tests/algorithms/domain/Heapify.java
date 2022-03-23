package russell.tests.algorithms.domain;

import java.util.Arrays;

public class Heapify {

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};
        heapify(arr, 0);
        
        System.out.println(Arrays.toString(arr));
    }
    
    public static void heapify(final int[] arr, final int index) {
        int largest = index;  // Initialize largest as root
        int left = 2*index + 1;  // left = 2*i + 1
        int right = 2*index + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (left < arr.length && arr[left] > arr[largest])
            largest = left;
 
        // If right child is larger than largest so far
        if (right < arr.length && arr[right] > arr[largest])
            largest = right;
 
        // If largest is not root
        if (largest != index) {
            int swap = arr[index];
            arr[index] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, largest);
        }
    }
}
