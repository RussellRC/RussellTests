package russell.tests.algorithms.practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/mandragora
 */
public class MandragoraForest {

    public static void main(String[] args) {
        
        final Scanner s = new Scanner(System.in);
        final int cases = s.nextInt();
        
        for (int i = 0; i < cases; i++) {
            long maxExp = maxExperience(s);
            System.out.println(maxExp);
        }
        
    }

    public static long maxExperience(final Scanner s) {
        int n = s.nextInt();
        long totalHP = 0;
        long[] mandragoras = new long[n];
        for (int i = 0; i < n; i++) {
            mandragoras[i] = s.nextLong();
            totalHP = totalHP + mandragoras[i];
        }
        
        if (mandragoras.length == 1) {
            return mandragoras[0];
        }
        
        Arrays.sort(mandragoras);
        long xp = 0;
        long str = 1;
        long eatHP = 0;
        for (int i = 0; i < mandragoras.length; i++) {
            eatHP = eatHP + mandragoras[i];
            
            // eating a monster causes str to increase by one, 
            // but decreases the max XP we can get by health of eaten mandragora  
            long newXP = (str + 1) * (totalHP - eatHP);
            
            // keep battling mandragoras until there is no increase in xp
            if (newXP > xp) {
                xp = newXP;
                str++;
            } else {
                return xp;
            }
        }
        return xp;
    }
    
}
