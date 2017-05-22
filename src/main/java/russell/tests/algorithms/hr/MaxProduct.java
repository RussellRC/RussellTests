package russell.tests.algorithms.hr;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
 */
public class MaxProduct {

    
    public static void main(String[] args) {
        
        System.out.println(maxProductWithPartitions(2));
        System.out.println(maxProductWithPartitions(3));
        System.out.println(maxProductWithPartitions(4));
        System.out.println(maxProductWithPartitions(5));
        System.out.println(maxProductWithPartitions(10));
        
        System.out.println(maxProduct(2));
        System.out.println(maxProduct(3));
        System.out.println(maxProduct(4));
        System.out.println(maxProduct(5));
        System.out.println(maxProduct(10));
    }
    
    
    public static int maxProduct(final int n) {
        
        if (n == 0 || n == 1) {
            return 0;
        }
        
        int maxProduct = 0;
        for (int i = 1; i <= n; i++) {
            int m1 = i*(n-i);
            int m2 = maxProduct(n-i) * i;
            maxProduct = Math.max(Math.max(maxProduct, m1), m2);
        }
        
        return maxProduct;
    }
    
    /**
     * This sux
     */
    public static int maxProductWithPartitions(final int n) {
        
        List<Integer> partial = new ArrayList<>();
        List<List<Integer>> partitions = new ArrayList<>();
        maxProduct(n, partial, partitions);
        int maxProduct = 1;
        for (List<Integer> partition : partitions) {
            int product = partition.stream().reduce(1, (a, b) -> a*b);
            if (product > maxProduct) {
                maxProduct = product;
            }
        }
        
        return maxProduct;
    }

    private static void maxProduct(int n, List<Integer> partial, List<List<Integer>> partitions) {
        if (n == 0) {
            final List<Integer> p = new ArrayList<>(partial);
            if (partitions.isEmpty()) {
                // add 0 to the partition of the origin number
                p.add(0);
            }
            partitions.add(p);
        }
        
        for (int i = n; i > 0; i--) {
            if (partial.isEmpty() || partial.get(partial.size()-1) >= i) {
                partial.add(i);
                maxProduct(n-i, partial, partitions);
                partial.remove(partial.size()-1);
            }
        }
    }
    
}
