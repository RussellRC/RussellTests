package russell.tests.algorithms.practice;

import java.util.Collections;
import java.util.PriorityQueue;


/**
 * http://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
 */
public class MedianOfStream {

    static int median = 0;
    
    /** Max-heap on the elements smaller than the median */ 
    static final PriorityQueue<Integer> left = new PriorityQueue<>(10, Collections.reverseOrder());
    
    /** Min-heap on the elements greater than the median */
    static final PriorityQueue<Integer> right = new PriorityQueue<>();
    
    public static void main(String[] args) {
        int arr[] = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
        printMedian(arr);
    }
    
    private static void printMedian(int[] arr) {
        
        for (int i = 0; i < arr.length; i++) {
            System.out.println(getMedian(arr[i]));
        }
        
    }

    private static int getMedian(int element) {
        if (left.size() == right.size()) {
            if (element < median) {
                left.add(element);
                median = left.peek();
            } else {
                right.add(element);
                median = right.peek();
            }
        } else if (left.size() > right.size()) {
            if (element < median) {
                right.add(left.remove());
                left.add(element);
            } else {
                right.add(element);
            }
            median = (right.peek() + left.peek()) / 2;
        } else {
            if (element < median) {
                left.add(element);
            } else {
                left.add(right.remove());
                right.add(element);
            }
            median = (right.peek() + left.peek()) / 2;
        }
        
//        System.out.println("l: " + left);
//        System.out.println("r: " + right);
        
        return median;
    }

    
}
