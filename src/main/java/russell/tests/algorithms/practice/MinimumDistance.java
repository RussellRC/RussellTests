package russell.tests.algorithms.practice;

/**
 * http://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
 * 
 * Given an unsorted array arr[] and two numbers x and y,
 * find the minimum distance between x and y in arr[].
 * The array might also contain duplicates.
 * You may assume that both x and y are different and present in arr[].
 */
public class MinimumDistance {

    public static void main(String[] args) {

        int[] arr = { 1, 2 };
        System.out.println(minDistance(arr, 1, 2));
        
        arr = new int[] { 3, 4, 5 };
        System.out.println(minDistance(arr, 3, 5));

        arr = new int[] { 3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3 };
        System.out.println(minDistance(arr, 3, 6));

        arr = new int[] { 2, 5, 3, 5, 4, 4, 2, 3 };
        System.out.println(minDistance(arr, 3, 2));
        System.out.println(minDistance(arr, 6, 7));
        System.out.println(minDistance(arr, 3, 9));
    }

    private static int minDistance(final int[] arr, final int x, final int y) {
        
        int prev = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x || arr[i] == y) {
                prev = i;
                break;
            }
        }
        if (prev == -1) {
            return 0;
        }
        
        int minDist = 0;
        for (int i = prev+1; i < arr.length; i++) {
            if (arr[i] == x || arr[i] == y) {
                if (arr[i] != arr[prev]) {
                    if (minDist == 0) {
                        minDist = i - prev;
                    } else if (i - prev < minDist) {
                        minDist = i - prev;
                    }
                } else {
                    prev = i;
                }
            }
        }
        return minDist;
    }

}
