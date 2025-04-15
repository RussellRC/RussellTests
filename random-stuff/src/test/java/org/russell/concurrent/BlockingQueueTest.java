package org.russell.concurrent;

import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlockingQueueTest {

  private BlockingQueue<Integer> bq;

  @BeforeEach
  void beforeEach() {
    bq = new BlockingQueue<>(1);
  }

  @Test
  void test1() {
    IntStream.range(0, 2)
        .forEach(
            i -> {
              final Thread t =
                  new Thread(
                      () -> {
                        System.out.printf("Putter thread %d putting...%n", i);
                        bq.put(i);
                        System.out.printf("Putter thread put %d...%n", i);
                      },
                      "Putter " + i);
              t.start();
            });

    IntStream.range(0, 10)
        .forEach(
            i -> {
              final Thread t =
                  new Thread(
                      () -> {
                        System.out.printf("Taker thread %d taking...%n", i);
                        final Integer take = bq.take();
                        System.out.printf("Taker thread %d took %d...%n", i, take);
                      });
              t.start();
            });
  }
}
