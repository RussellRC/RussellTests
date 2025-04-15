package org.russell.practice.unsorted;

import java.util.Arrays;

/**
 * Drop N eggs from K floors.
 * What is the least number of egg-droppings that is guaranteed to work in all cases?
 * 
 */
public class EggDrop {

    public static void main(String[] args) {

        final int eggs = 2;
        final int floors = 10;
        
        System.out.println(eggDrop(eggs, floors));
        
    }

    private static int eggDrop(final int eggs, final int floors) {
        
        final int[][] mem = new int[eggs+1][floors+1];
        
        for (int egg=1; egg <= eggs; egg++) {
            // 0 trials for 0 floors and 1 trial for 1 floor 
            mem[egg][0] = 0;
            mem[egg][1] = 1;
        }
        
        for (int floor=1; floor <= floors; floor++) {
            // we always need to try all floors with 1 egg
            mem[1][floor] = floor;
        }
        
        for (int e=2; e <= eggs; e++) {
            for (int f = 2; f <= floors; f++) {
                
                // Worst case, we try every floor
                // but we can do better than that with more than 1 egg
                mem[e][f] = f;
                for (int i = 1; i <= f; i++) {
                    
                    // if egg breaks at floor, get result for 1 less egg and 1 less floor
                    int trialsBroken = mem[e-1][i-1];  
                    
                    // if egg doesn't break, get result with same eggs and remaining floors (f-i)
                    int trialsSurvive = mem[e][f-i];
                    
                    // Add one to the worst case scenario
                    int trials = 1 + Math.max(trialsBroken, trialsSurvive);
                    
                    // Minimize number of trials
                    if (trials < mem[e][f]) {
                        mem[e][f] = trials;
                    }
                }
            }
        }
        
        System.out.println(Arrays.deepToString(mem));
        return mem[eggs][floors];
    }
    
    
}
