package org.russell.algorithms.practice.unsorted;

public class Profit {

    public static void main(String[] args) {
        
        //int[] prices = {100, 90, 80, 70, 60};
        int[] prices = {60, 70, 80, 90, 100};
        
        int profit = 0;
        int minElement = Integer.MAX_VALUE;
        for(int i=0; i<prices.length; i++){
           profit = Math.max(profit, prices[i]-minElement);
           minElement = Math.min(minElement, prices[i]);
        }
        System.out.println(profit);
    }
}
