package org.russell.practice.hr;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/2d-array
 * @author russellrazo
 *
 */
public class HourGlass {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        final Scanner scan = new Scanner(System.in);
        final int length = 6;
        final String[][] matrix = new String[length][length];
        
        for (int i = 0; i < length; i++) {
            matrix[i] = scan.nextLine().split("\\s+");
        }
        
        int maxSum = Integer.MIN_VALUE;
        for (int y = 0; y+2 < matrix.length; y++) {
            for (int x = 0; x+2 < matrix[y].length; x++) {
                int hgSum = hgSum(matrix, y, x);
                if (hgSum > maxSum) {
                    maxSum = hgSum;
                }
            }
        }
        
        System.out.print(maxSum);
    }
    
    private static int hgSum(final String[][] matrix, final int y, final int x) {
        int sum = Integer.parseInt(matrix[y][x]) + Integer.parseInt(matrix[y][x+1]) + Integer.parseInt(matrix[y][x+2]);
        sum = sum + Integer.parseInt(matrix[y+1][x+1]);
        sum = sum + Integer.parseInt(matrix[y+2][x]) + Integer.parseInt(matrix[y+2][x+1]) + Integer.parseInt(matrix[y+2][x+2]);
        return sum;
    }
}