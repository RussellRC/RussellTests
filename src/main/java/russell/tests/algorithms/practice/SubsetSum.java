package russell.tests.algorithms.practice;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
 */
public class SubsetSum {

    
    public static void main(String[] args) {
        
        int[] set = {3, 34, 4, 12, 5, 2};
        
        System.out.println(hasSubsetRec(9, set, set.length-1));
        System.out.println(hasSubsetRec(10, set, set.length-1));
        System.out.println(hasSubsetRec(13, set, set.length-1));
        
        System.out.println(hasSubset(9, set));
        System.out.println(hasSubset(10, set));
        System.out.println(hasSubset(13, set));
    }

    private static boolean hasSubset(int sum, int[] set) {
        
        boolean solution[][] = new boolean[sum+1][set.length+1];
        
        for (int i = 0; i <= set.length; i++) {
            // if sum is 0, there is a solution
            solution[0][i] = true;
        }
        
        // This is really not necessary since all elements are false
        for (int s = 1; s <= sum; s++) {
            // if sum is not 0, but there are no elements, there is no solution
            solution[s][0] = false;
        }
        
        for (int s = 1; s <= sum; s++) {
            for (int i = 1; i <= set.length; i++) {
                
                solution[s][i] = solution[s][i-1];
                if (s >= set[i-1]) {
                    solution[s][i] = solution[s][i] || solution[s - set[i-1]][i-1];
                }
                
            }
        }
        
        //System.out.println(Arrays.deepToString(solution));
            
        return solution[sum][set.length];
    }
    
    private static boolean hasSubsetRec(int sum, int[] set, int i) {
        
        if (sum == 0) {
            return true;
        }
        if (sum > 0 && i < 0) {
            return false;
        }
        
        if (set[i] > sum) {
            return hasSubsetRec(sum, set, i-1);
        }
        
        // diff including last element || sum
        return hasSubsetRec(sum-set[i], set, i-1) || hasSubsetRec(sum, set, i-1);
    }
    
}
