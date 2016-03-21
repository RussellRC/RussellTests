package russell.tests.algorithms.practice.cyan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Solution {

    public static void main(String args[]) throws Exception {
        doCities();
        // doMaxOccurrences();
        // doStrings();
    }

    private static void doCities() {
        // Sample input
        // 7 => clinics
        // 2 => cities
        // 200000 => population C1
        // 500000 => population C2

        // Result:
        // 100000 on largest clinic

        // Objective: minimize the ppl on largest clinic (or largest city??)
        // each city must have at least 1 clinic

        // System.out.println(partition(7));
        
        // sort cities by population and sort paritions
        // 1,6 => 200k,83k
        // 2,5 => 100k,100k
        // 3,4 => 66k,125k
    }

    private static void doStrings() {
        // sample input:
        // I do code at hacker rank at midnight
        // do at hacker midnight

        final Scanner scan = new Scanner(System.in);
        final String s = scan.nextLine();
        final String t = scan.nextLine();

        final List<String> listS = new ArrayList<>(Arrays.asList(s.split("\\s+")));
        final List<String> listT = new ArrayList<>(Arrays.asList(t.split("\\s+")));

        for (String str : listT) {
            listS.remove(str);
        }

        System.out.println(listS);
    }

    private static void doMaxOccurrences() {
        // Sample input:
        // 5
        // 2 4 26
        // abcdabcdabcdab

        final Scanner scan = new Scanner(System.in);
        final int length = Integer.parseInt(scan.nextLine());

        String[] split = scan.nextLine().split("\\s+");
        final int minChars = Integer.parseInt(split[0]);

        final int maxChars = Integer.parseInt(split[1]);
        final int uniqueChars = Integer.parseInt(split[2]);

        final String string = scan.nextLine();

        final TreeMap<Integer, List<String>> substrings = substrings(string, minChars, maxChars, uniqueChars);
        System.out.println(substrings);
        System.out.println("max: " + substrings.lastEntry().getKey());
    }

    private static TreeMap<Integer, List<String>> substrings(String string, int minChars, int maxChars,
        int uniqueChars) {
        final Set<String> substrings = new HashSet<>();
        final TreeMap<Integer, List<String>> ocurrencesToStrings = new TreeMap<>();

        for (int index = 0; index < string.length(); index++) {
            for (int chars = minChars; chars <= maxChars; chars++) {
                try {
                    // This can be optimized
                    final String substring = string.substring(index, index + chars);
                    if (!substrings.contains(substring) && hasUniqueChars(substring, uniqueChars)) {
                        substrings.add(substring);
                        final int occurrences = occurrences(string, substring);
                        List<String> value = ocurrencesToStrings.get(occurrences);
                        if (value == null) {
                            value = new ArrayList<>();
                            ocurrencesToStrings.put(occurrences, value);
                        }
                        value.add(substring);
                    }
                } catch (IndexOutOfBoundsException e) {
                    // fix later
                }
            }
        }

        return ocurrencesToStrings;
    }

    private static boolean hasUniqueChars(String substring, int uniqueChars) {
        final Set<Character> unique = new HashSet<>();
        for (int i = 0; i < substring.length(); i++) {
            if (!unique.add(substring.charAt(i)) || unique.size() > uniqueChars) {
                return false;
            }
        }
        return true;
    }

    private static int occurrences(String string, String substring) {
        int count = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = string.indexOf(substring, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex = lastIndex + substring.length();
            }
        }
        return count;
    }

}