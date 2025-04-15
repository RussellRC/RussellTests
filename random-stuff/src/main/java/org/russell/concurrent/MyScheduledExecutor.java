package org.russell.concurrent;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** Just exploring how to implement a Scheduled Task Executor.<br> */
public class MyScheduledExecutor extends Thread {

  private final Lock lock;
  private final Condition condition;
  private final PriorityBlockingQueue<Task> tasks;
  private boolean killed = false;

  private MyScheduledExecutor() {
    super("DelayedExecutor");
    lock = new ReentrantLock();
    condition = lock.newCondition();
    tasks = new PriorityBlockingQueue<>(10, Comparator.comparingLong(t -> t.time));
  }

  @Override
  public void run() {
    lock.lock(); // A ReentrantLock is owned by the thread last successfully locking
    try {
      while (!killed) {
        if (tasks.isEmpty()) {
          System.out.println("Awaiting until signaled");
          condition.await();
        } else {
          final long await = tasks.peek().time - System.currentTimeMillis();
          System.out.println("Awaiting " + await);
          condition.await(await, TimeUnit.MILLISECONDS);
        }
        System.out.println("I've been awaken at " + System.currentTimeMillis());
        if (!tasks.isEmpty() && System.currentTimeMillis() >= tasks.peek().time) {
          new Thread(tasks.poll().runnable).start();
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }

  public void submitTask(final long millisFromNow, final Runnable runnable) {
    lock.lock();
    try {
      final long time = System.currentTimeMillis() + millisFromNow;
      tasks.add(new Task(time, runnable));
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  public void kill() {
    killed = true;
    lock.lock();
    try {
      condition.signal();
    } finally {
      lock.unlock();
    }
  }

  /** A task to be run in the future */
  private record Task(long time, Runnable runnable) {}

  /** main */
  public static void main(String[] args) throws InterruptedException {
    final var ex = new MyScheduledExecutor();
    ex.start();

    Thread.sleep(1000);

    System.out.println("Submitting task from main...");
    ex.submitTask(2000, () -> System.out.println("hello 1"));
    ex.submitTask(2500, () -> System.out.println("hello 2"));
    ex.submitTask(3000, () -> System.out.println("hello 3"));
    ex.submitTask(3500, () -> System.out.println("hello 4"));

    final Thread killer =
        new Thread(
            () -> {
              try {
                Thread.sleep(10000);
                ex.kill();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            });
    killer.start();

    System.out.println("joining killer");
    killer.join();

    System.out.println("joining delayed executor");
    ex.join();
  }
}
