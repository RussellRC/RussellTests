package russell.tests.algorithms.exp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Amz {

    public static void main(String args[]) throws Exception {
        //testSumValue();
        testSecondLeastPopular();
    }
    
    public static void testSecondLeastPopular() throws NoSecondLeastPopular {
        
        System.out.println(getSecondLeastPopular(Arrays.asList(4, 4, 4, 4, 2, 3, 3, 5, 5, 5)));
        System.out.println(getSecondLeastPopular(Arrays.asList(1, 2, 3, 3, 4, 4)));
        try {
            System.out.println(getSecondLeastPopular(Arrays.asList(1, 2, 3, 4, 5)));            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
        System.out.println("finished");
    }
    
    public static void testSumValue() throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nm = br.readLine();
        String[] split = nm.split(" ");
        final int n = Integer.parseInt(split[0]);
        final int m = Integer.parseInt(split[1]);
        int[] array = new int[n];
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            String[] split2 = line.split(" ");
            sumValue(array, Integer.parseInt(split2[0]), Integer.parseInt(split2[1]), Integer.parseInt(split2[2]));
        }
        System.out.print("" + getMaximum(array));
    }

    static void sumValue(int[] array, int from, int to, int value) {
        for (int i = from - 1; i <= to - 1; i++) {
            array[i] = array[i] + value;
        }
    }

    static int getMaximum(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static Integer getSecondLeastPopular(List<Integer> pageIDs) throws NoSecondLeastPopular {
        
        final Map<Integer, Integer> byId = new HashMap<>();
        for (Integer id : pageIDs) {
            Integer count = byId.get(id);
            if (count == null) {
                byId.put(id, 1);
            } else {
                byId.put(id, ++count);
            }
        }
        
        final Map<Integer, Set<Integer>> byPopularity = new HashMap<>();
        final TreeSet<Integer> popularities = new TreeSet<>();
        for (Map.Entry<Integer, Integer> entry : byId.entrySet()) {
            Set<Integer> set = byPopularity.get(entry.getValue());
            if (set == null) {
                set = new HashSet<Integer>();
            }
            set.add(entry.getKey());
            byPopularity.put(entry.getValue(), set);
            
            popularities.add(entry.getValue());
        }
        
        popularities.pollFirst();
        Integer secondLeastPopularity = popularities.pollFirst(); 
        
        if (secondLeastPopularity == null) {
            throw new NoSecondLeastPopular("there is no second least popular");
        }
        
        Set<Integer> set = byPopularity.get(secondLeastPopularity);
        if (set == null) {
            throw new NoSecondLeastPopular("there is no second least popular");
        }
        
        Integer secondLeastId = null;
        for (Integer id : set) {
            if (secondLeastId == null) {
                secondLeastId = id;
            } else {
                secondLeastId = resolveTie(id, secondLeastId);
            }
        }
        
        return secondLeastId;
    }

    
    private static class NoSecondLeastPopular extends Exception {     
        public NoSecondLeastPopular(String msg) {
            super(msg);
        }
    }
    
    
    // Helper class to resolve ties
    private static Integer resolveTie(Integer idOne, Integer idTwo) {
        return idOne <= idTwo ? idOne : idTwo;
    }
}
