package org.russell.concurrent;

public class Livelock {

  enum Side {
    LEFT,
    RIGHT
  }

  static class Person {
    private final String name;
    private Side side;

    public Person(final String name, final Side side) {
      this.name = name;
      this.side = side;
    }

    public void checkCorridor() {
      System.out.println(name + " is on " + side);
      try {
        Thread.sleep(100); // Simulate action
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    public void passOrMove(final Person other) {
      while (true) {
        checkCorridor();
        // I'm facing the other person in a corridor
        if (other.side != this.side) {
          // switch side
          switch (side) {
            case LEFT -> side = Side.RIGHT;
            case RIGHT -> side = Side.LEFT;
          }
          System.out.format("%s moved to %s to let %s pass %n", name, side, other.name);
          try {
            Thread.sleep(200); //Simulate some delay.
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        } else {
          System.out.println(name + " passed");
          break;
        }
      }
    }
  }

  public static void main(String[] args) {
    final Person alphonse = new Person("Alphonse", Side.LEFT);
    final Person gaston = new Person("Gaston", Side.RIGHT);
    new Thread(() -> alphonse.passOrMove(gaston)).start();
    new Thread(() -> gaston.passOrMove(alphonse)).start();
  }
}
