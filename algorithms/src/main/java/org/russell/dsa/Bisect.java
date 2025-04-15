package org.russell.dsa;

public class Bisect {

    static int bisectRight(int[] arr, int x, int low, int high) {
        while (low < high) {
            int mid = (low + high)/2;
            if (x < arr[mid]) {
                high = mid;
            }
            else {
                low = mid+1;
            }

        }
        return low;
    }

    static int bisectLeft(int[] arr, int x, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] < x) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
}
