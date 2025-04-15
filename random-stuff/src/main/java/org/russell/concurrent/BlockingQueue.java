package org.russell.concurrent;

import java.util.Deque;
import java.util.LinkedList;

public class BlockingQueue<T> {

  private final Deque<T> queue;
  private final int limit;

  public BlockingQueue(final int limit) {
    this.queue = new LinkedList<>();
    this.limit = limit;
  }

  /** No race condition because the method itself is synchronized */
  public synchronized void put(T item) {
    while (queue.size() == limit) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName() + " was interrupted");
        throw new RuntimeException(e);
      }
    }
    if (queue.isEmpty()) {
      notifyAll(); // wake all
    }
    queue.add(item);
  }

  /** No race condition because the method itself is synchronized */
  public synchronized T take() {
    while (queue.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }
    if (queue.size() == limit) {
      notifyAll();
    }
    return queue.poll();
  }
}
