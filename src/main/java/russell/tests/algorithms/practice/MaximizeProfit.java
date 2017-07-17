package russell.tests.algorithms.practice;

/**
 * http://www.geeksforgeeks.org/stock-buy-sell/
 */
public class MaximizeProfit {

    public static void main(String[] args) {
        
        stockBuySell(new int[]{100, 180, 260, 310, 40, 535, 695});
        
    }

    private static void stockBuySell(int[] arr) {
        if (arr.length < 2) {
            throw new RuntimeException("not enough days");
        }
        
        int buy = 0;
        int sell;
        for (int i = 0; i < arr.length; i++) {
            
            while (buy+1 < arr.length && arr[buy] >= arr[buy+1]) {
                buy++;
            }
            if (buy == arr.length - 1) {
                break;
            }
            sell = buy+1;
            while(sell+1 < arr.length && arr[sell] <= arr[sell+1] ) {
                sell++;
            }
            System.out.println(String.format("buy on day %d, sell on %d", buy, sell));
            buy = sell + 1;
            i = buy;
        }
    }
    
}
