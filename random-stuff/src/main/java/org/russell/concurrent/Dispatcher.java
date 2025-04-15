package org.russell.concurrent;

import java.util.Comparator;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dispatcher {

  final Lock lock = new ReentrantLock();
  final Condition noReaders = lock.newCondition();

  static final Comparator<Message> SEVERITY_COMPARATOR = Comparator.comparingInt(m -> m.severity);
  final PriorityBlockingQueue<Message> messages =
      new PriorityBlockingQueue<>(10, SEVERITY_COMPARATOR);
  final Deque<Reader> readers = new ConcurrentLinkedDeque<>();
  final Map<Message, Reader> dispatched = new ConcurrentHashMap<>();

  public record Message(String id, int severity, long timestamp) {}

  public record Reader(String id) {}

  public void addReader(final Reader reader) {
    lock.lock();
    try {
      readers.add(reader);
      noReaders.signal();
    } finally {
      lock.unlock();
    }
  }

  public Optional<Reader> pollReader() {
    return Optional.ofNullable(readers.pollFirst());
  }

  public void addMessage(final Message message) {
    messages.add(message);
  }

  public Message takeMessage() throws InterruptedException {
    return messages.take();
  }

  public void dispatch() {
    while (true) {
      try {
        lock.lock();
        System.out.println("Taking message...");
        final Message message = takeMessage(); // this will block if no messages
        System.out.printf("Took message %s%n", message.id);
        while (readers.isEmpty()) {
          // Could use a blocking queue as well, but being fancy...
          System.out.printf("No readers, waiting... %s%n", message.id);
          noReaders.await();
        }
        pollReader()
            .ifPresent(
                reader -> {
                  System.out.printf("Assigning message %s to reader %s%n", message.id, reader.id);
                  dispatched.put(message, reader);
                });
      } catch (InterruptedException e) {
        System.out.println("Interrupted!");
        Thread.currentThread().interrupt();
      } finally{
        lock.unlock();
      }
    }
  }

  public static void main(String[] args) throws Exception {
    final Dispatcher dispatcher = new Dispatcher();
    final Thread t = new Thread(dispatcher::dispatch);
    t.start();

    Thread.sleep(1000);
    dispatcher.addMessage(new Message("m1", 1, System.currentTimeMillis()));
    Thread.sleep(500);
    dispatcher.addReader(new Reader("r1"));

    dispatcher.addMessage(new Message("m4", 4, System.currentTimeMillis()));
    dispatcher.addMessage(new Message("m3", 3, System.currentTimeMillis()));
    dispatcher.addMessage(new Message("m2", 2, System.currentTimeMillis()));

    dispatcher.addReader(new Reader("r2"));
    dispatcher.addReader(new Reader("r3"));
  }
}
