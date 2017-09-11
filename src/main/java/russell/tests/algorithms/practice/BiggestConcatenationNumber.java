package russell.tests.algorithms.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * http://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
 * Given an array of numbers, what is the largest number possible by concatenating all the numbers together.
 */
public class BiggestConcatenationNumber {

    public static Comparator<Integer> myComparator = (x, y) -> {
        int c1 = Integer.parseInt(x.toString() + y.toString());
        int c2 = Integer.parseInt(y.toString() + x.toString());
        return Integer.compare(c1, c2);
    };
    
    public static void main(String[] args) {
        System.out.println(biggestNumber(Arrays.asList(54, 546, 548, 60)));
        System.out.println(biggestNumber(Arrays.asList(1, 34, 3, 98, 9, 76, 45, 4)));
    }
    
    public static String biggestNumber(final List<Integer> list) {
        Collections.sort(list, myComparator.reversed());
        final String num = list.stream().map(e -> e.toString()).collect(Collectors.joining());
        return num;
    }
    
    
}
