package russell.tests.leak;

public class Example implements IExample {
    private int counter;
    private ILeak leak;

    private static final long[] cache = new long[1000000];

    public int plusPlus() {
        return counter++;
    }

    public int counter() {
        return counter;
    }

    public ILeak leak() {
        return new Leak(leak);
    }

    public IExample copy(IExample example) {
        if (example != null) {
            counter = example.counter();
            leak = example.leak();
        }
        return this;
    }

    public String message() {
        return "Version 4";
    }

}
