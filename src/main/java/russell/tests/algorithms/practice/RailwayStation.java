package russell.tests.algorithms.practice;

/**
 * There are “n” ticket windows in the railway station.
 * ith window has a[i] tickets available.
 * Price of a ticket is equal to the number of tickets remaining in that window at that time.
 * When “m” tickets have been sold, what’s the maximum amount of money the railway station can earn?
 * Example. n=2, m=4 in 2 window available tickets are : 2 , 5
 * from 2nd wicket sold 4 tickets so 5+4+3+2=14
 * 
 */
public class RailwayStation {

    public static void main(String[] args) {
        final int windows = 2;
        final int[] tickets = {2, 5};
        final int sold = 4;
        
        System.out.println(maxMoney(windows, tickets, sold));
    }

    private static int maxMoney(int windows, int[] tickets, int sold) {
        int max = 0;
        int i = tickets.length-1;
        
        // assuming ticket prices are sorted
        while (sold > 0 && i >= 0) {
            if (tickets[i] >= sold) {
                max += tickets[i];
                tickets[i] = tickets[i] - 1;
                sold--;
            } else {
                i--;
            }
        }
        
        return max;
    }
    
}
