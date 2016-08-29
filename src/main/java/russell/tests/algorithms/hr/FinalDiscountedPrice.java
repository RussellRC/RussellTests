package russell.tests.algorithms.hr;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * the shoekeeper gives a discount di, which is the price of the first item to its right satisfying di <= price;
 * if not such item satisfies this inequality, then the shoekeeper does not discount.
 */
public class FinalDiscountedPrice {

    public static void main(String[] args) {
        
        int[] prices1 = {5, 1, 3, 4, 6, 2};
        // discounts = {1, 0 , 2, 2, 2, 0}
        //total = 14
        //no discount indices = 1, 5
        
        int[] prices2 = {1, 3, 3, 2, 5};
        // discounts = {0, 3, 2, 0, 0}
        //total = 9
        //no discount indices = 0, 3, 4
        
        finalPrices(prices1);
        finalPrices(prices2);
        
    }
    
    
    private static void finalPrices(int[] prices) {
        int[] discounts = new int[prices.length];
        discounts[discounts.length-1] = 0;
        
        // Last element is never discounted
        int lastDiscount = prices[prices.length - 1];
        int total = lastDiscount;
        final Deque<Integer> noDiscountIndexes = new LinkedList<>();
        noDiscountIndexes.addFirst(discounts.length-1);
        
        for (int i = discounts.length-2; i >= 0; i--) {
            if (lastDiscount <= prices[i]) {
                discounts[i] = lastDiscount;
            } else {
                discounts[i] = 0;
                lastDiscount = 0;
                noDiscountIndexes.addFirst(i);
            }
            
            if (i > 0 && prices[i] <= prices[i-1]) {
                lastDiscount = prices[i];
            }
            
            total += (prices[i] - discounts[i]);
        }
        
        System.out.println(Arrays.toString(discounts));
        System.out.println(total);
        System.out.println(noDiscountIndexes.stream().map(Object::toString).collect(Collectors.joining(" ")));
        System.out.println("==========");
    }
}
