package russell.tests.algorithms.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * @see {@link http://www.ideserve.co.in/learn/coin-change-problem-number-of-ways-to-make-change}
 */
public class CoinsChange {

//     public static void main(String[] args) {
//    
//     final Scanner scan = new Scanner(System.in);
//    
//     final int number = scan.nextInt();
//     final int size = scan.nextInt();
//     final Set<Integer> coins = new LinkedHashSet<>();
//    
//     for (int i = 0; i < size; i++) {
//     coins.add(scan.nextInt());
//     }
//     System.out.println(number);
//     System.out.println(coins);
//    
//     final List<Integer> partial = new ArrayList<>();
//     final List<List<Integer>> solutions = new ArrayList<>();
//    
//     final Map<Integer, Integer> partialMap = new HashMap<>();
//     final Set<Map<Integer, Integer>> solutionsSet = new HashSet<>();
//    
//     //findChange(number, number, coins, 0, partial, solutions, partialMap, solutionsSet);
//     findChange(number, number, coins, 0, partialMap, solutionsSet);
//    
//     System.out.println(solutionsSet);
//     System.out.println(solutionsSet.size());
//     }

    public static void main(String[] args) {

        final Scanner scan = new Scanner(System.in);

        final int number = scan.nextInt();
        final int size = scan.nextInt();

        final int[] coins = new int[size];

        for (int i = 0; i < size; i++) {
            coins[i] = scan.nextInt();
        }
        Arrays.sort(coins);
        
        int[][] memo = new int[number + 1][size + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        final Map<AmountAndCoin, Long> combinationsMap = new LinkedHashMap<>();
        final long combinations = changeCombinations(number, coins, coins.length, combinationsMap);
        System.out.println(combinationsMap);
        System.out.println(combinations);
        
//        final int findChange = findChange(number, coins, size, memo);
//        printMemo(memo);
//        System.out.println(findChange);
    }
    
    private static void printMemo(int[][] memo) {
        for (int i = 0; i < memo.length; i++) {
            System.out.println(Arrays.toString(memo[i]));
        }
        System.out.println("==========");
    }
    
    private static long changeCombinations(int amount, int[] coins, int coinIndex, Map<AmountAndCoin, Long> combinationsMap) {
        if (amount == 0) {
            return 1;
        } else if (amount < 0) {
            return 0;
        } else if (coinIndex <= 0) {
            return 0;
        }

        final AmountAndCoin ac = new AmountAndCoin(amount, coinIndex);
        final Long value = combinationsMap.get(ac);
        if (value != null) {
            return value;
        }
        
        long combinations2 = changeCombinations(amount - coins[coinIndex-1], coins, coinIndex, combinationsMap);
        long combinations1 = changeCombinations(amount, coins, coinIndex-1, combinationsMap);
        long combinations = combinations1 + combinations2;
        combinationsMap.put(ac, combinations);
        
        return combinations;
    }
    
    public static int findChange(int sum, int[] coins, int coin_num, int[][] memo) {
        if (sum == 0) {
            return 1;
        } else if (sum < 0) {
            return 0;
        } else if (coin_num <= 0) {
            return 0;
        } else if (memo[sum][coin_num] != -1) {
            return memo[sum][coin_num];
        } else {
            int change2 = findChange(sum - coins[coin_num - 1], coins, coin_num, memo);
            int change1 = findChange(sum, coins, coin_num - 1, memo);
            memo[sum][coin_num] = change1 + change2;
            return memo[sum][coin_num];
        }
    }

    private static class AmountAndCoin {
        int amount;
        int coinIndex;
        
        private AmountAndCoin(final int amount, final int coinIndex) {
            this.coinIndex = coinIndex;
            this.amount = amount;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + amount;
            result = prime * result + coinIndex;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (!(obj instanceof AmountAndCoin))
                return false;
            AmountAndCoin other = (AmountAndCoin) obj;
            if (amount != other.amount)
                return false;
            if (coinIndex != other.coinIndex)
                return false;
            return true;
        }
        @Override
        public String toString() {
            return "{amount:" + amount + ", coin:" + coinIndex + "}";
        }
    }

    
    

    private static void findChange(int number, int n, Set<Integer> coins, int partialSum, List<Integer> partial,
                                   List<List<Integer>> partitions, Map<Integer, Integer> partialMap,
                                   Set<Map<Integer, Integer>> solutionsSet) {
        if (partialSum == number) {
            partitions.add(new ArrayList<>(partial));
            solutionsSet.add(new HashMap<>(partialMap));
        } else {
            for (int i : coins) {
                if (partialSum < number) {
                    partial.add(i);
                    partialSum = partialSum + i;

                    Integer value = partialMap.get(i);
                    if (value != null) {
                        partialMap.put(i, value + 1);
                    } else {
                        partialMap.put(i, 1);
                    }

                    findChange(number, number - i, coins, partialSum, partial, partitions, partialMap, solutionsSet);

                    int remove = partial.remove(partial.size() - 1);
                    partialSum = partialSum - remove;

                    value = partialMap.get(i);
                    if (value == 1) {
                        partialMap.remove(i);
                    } else {
                        partialMap.put(i, value - 1);
                    }
                }
            }
        }
    }

    private static void findChange(int number, int n, Set<Integer> coins, int partialSum, Map<Integer, Integer> partialMap,
                                   Set<Map<Integer, Integer>> solutionsSet) {
        if (partialSum == number) {
            solutionsSet.add(new HashMap<>(partialMap));
        } else {
            for (int i : coins) {
                if (partialSum < number) {
                    partialSum = partialSum + i;

                    Integer value = partialMap.get(i);
                    if (value != null) {
                        partialMap.put(i, value + 1);
                    } else {
                        partialMap.put(i, 1);
                    }

                    findChange(number, number - i, coins, partialSum, partialMap, solutionsSet);

                    value = partialMap.get(i);
                    if (value == 1) {
                        partialMap.remove(i);
                    } else {
                        partialMap.put(i, value - 1);
                    }
                    partialSum = partialSum - i;
                }
            }
        }
    }

}
