package russell.tests.algorithms.hr;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 */
public class RodCut {

    public static void main(String[] args) {
        
        int[] arr = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(maxValue(arr));
        
        arr = new int[]{3, 5, 8, 9, 10, 17, 17, 20};
        System.out.println(maxValue(arr));
    }
    
    public static int maxValue(int[] arr) {
        final int[] value = new int[arr.length + 1];
        value[0] = 0;
        
        for (int i=1; i<=arr.length; i++) {
            
            int max_value = arr[i-1];
            for (int j=0; j<i; j++) {
                max_value = Math.max(max_value, arr[j] + value[i-j-1]);
            }
            value[i] = max_value;
            System.out.println(Arrays.toString(value));
        }
        
        return value[arr.length];
    }
    
}
