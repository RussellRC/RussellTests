package russell.tests.algorithms;

import java.util.ArrayList;
import java.util.List;


public class Prime {

    public static void main(String[] args) {
        System.out.println(getPrimes(20));
    }
    
    public static List<Integer> getPrimes(int n) {
        List<Integer> result = new ArrayList<>();
        
        int num = 2;
        int i = 0;
        while (i < n) {
            if (isPrime(num)) {
                result.add(num);
                i++;
            }
            num++;
        }
        
        return result;
    }
    
    public static boolean isPrime(int n) {
        if (n > 2 && n % 2 == 0) {
            return false;
        }
        
        double sqrt = Math.sqrt(n);
        for (int i = 3; i < sqrt; i=i+2) {
            if ( n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
