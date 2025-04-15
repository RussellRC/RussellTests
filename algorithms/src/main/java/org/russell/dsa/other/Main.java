package org.russell.dsa.other;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    final Random random = new Random();
    final Map<Integer, Long> frequencies =
        IntStream.range(0, 50)
            .mapToObj(i -> random.nextInt(20))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    System.out.println(frequencies);
  }
}
