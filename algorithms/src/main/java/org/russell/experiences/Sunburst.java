package org.russell.experiences;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Sunburst {

    /**
     * The original problem would read multiple lines and send all of them as input data,
     * there is a missing piece to split the input data by lines
     * and call executeLine for each one, accumulating all the results in a single StringBuilder
     * and returning the resulting String
     */
    public static void main(String[] args) {
        System.out.println(executeLine("fibonacci 21"));
        System.out.println(executeLine("overlap 8,17 21,30"));
        System.out.println(executeLine("overlap 8,17 21,30 6,10"));
        System.out.println(executeLine("stats"));
    }

    public static String executeLine(String inputData) {
        // Use this function to write your solution;

        final Invocation invocation;
        try {
            invocation = parseInput(inputData);
            switch (invocation.method) {
                case FIBONACCI:
                    updateStats(invocation.method);
                    return fibonacci(invocation.args);
                case OVERLAP:
                    updateStats(invocation.method);
                    return overlap(invocation.args);
                case STATS:
                    return stats();
                default:
                    return "error";
            }
        } catch (final IllegalArgumentException e) {
            //return "error";
            // for debugging purposes
            return e.getMessage();
        }
    }

    private static final Map<String, Method> METHOD_MAPPER = Map.of("fibonacci", Method.FIBONACCI,
            "overlap", Method.OVERLAP,
            "stats", Method.STATS);
    private static final Map<Method, Integer> STATS = new EnumMap<>(Method.class);

    /** Prints fibonacci secuence of to N */
    private static String fibonacci(final List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("1 number is required. Given " + args);
        }
        final int targetFib;
        try {
            targetFib = Integer.parseInt(args.get(0));
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Not a number: " + args);
        }
        if (targetFib < 1) {
            throw new IllegalArgumentException("Not a positive integer: " + args);
        }

        int fib0 = 0;
        int fib1 = 1;
        final StringBuilder result = new StringBuilder();
        result.append(fib0).append(" ").append(fib1);
        int fibN = fib0 + fib1;
        while (fibN <= targetFib) {
            result.append(" ").append(fibN);
            fib0 = fib1;
            fib1 = fibN;
            fibN = fib0 + fib1;
        }
        return result.toString();
    }

    /** Returns whether the given ranges overlap */
    private static String overlap(final List<String> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("At least 2 pairs of ranges must be given. Input: " + args);
        }

        final List<Interval> intervals = args.stream()
                .sorted()
                .map(Interval::parse)
                .collect(Collectors.toList());

        for (int i = 0; i < intervals.size()-1; i++) {
            final Interval rangeA = intervals.get(i);
            final Interval rangeB = intervals.get(i+1);
            if (overlap(rangeA, rangeB)) {
                return "true";
            }
        }
        return "false";
    }

    /**
     * Given two sorted ranges A and B (A < B) overlap if
     * B.first >= A.first and B.first <= A.second
     */
    private static boolean overlap(final Interval rangeA, final Interval rangeB) {
        return rangeB.first >= rangeA.first && rangeB.first <= rangeA.second;
    }

    /** Prints the current stats */
    private static String stats() {
        return String.format("%d %d",
                STATS.getOrDefault(Method.FIBONACCI, 0),
                STATS.getOrDefault(Method.OVERLAP, 0));
    }

    /** Increments the stats for the given method */
    private static void updateStats(final Method method) {
        STATS.compute(method, (k,v) -> (v==null) ? 0 : v++);
    }

    /**
     * Parses and validates the given input and maps it to an Invocation
     */
    private static Invocation parseInput(final String inputData) {
        if (inputData.isBlank()) {
            throw new IllegalArgumentException("Input data must not be blank: " + inputData);
        }

        // Splitting by space since the input contract for both methods is predefined
        final String[] split = inputData.split(" ");

        final Optional<Method> method;
        try {
            method = Method.fromString(split[0].trim());
        } catch (final ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Method name not provided: " + inputData);
        }
        if (method.isEmpty()) {
            throw new IllegalArgumentException("Unknown method: " + inputData);
        }
        if (method.get() == Method.STATS) {
            return new Invocation(method.get(), List.of());
        }
        try {
            final List<String> args = Arrays.stream(split, 1, split.length)
                    .map(String::trim)
                    .collect(Collectors.toUnmodifiableList());
            return new Invocation(method.get(), args);
        } catch (final ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Missing parameters: " + inputData, e);
        }
    }

    /** Holds a method name and its corresponding arguments */
    private final static class Invocation {
        final Method method;
        final List<String> args;
        public Invocation(final Method method, final List<String> args) {
            this.method = method;
            this.args = args;
        }
    }

    /**
     * Represents a Range
     */
    private final static class Interval implements Comparable<Interval> {
        final static Comparator<Interval> COMPARATOR = Comparator
                .comparing(Interval::getFirst)
                .thenComparing(Interval::getSecond);

        private final int first;
        private final int second;
        private Interval(final int first, final int second) {
            this.first = first;
            this.second = second;
        }

        /**
         * Parses a expected String a,b
         * Both must be integers and a <= b
         */
        static Interval parse(final String str) {
            final String[] split = str.split(",");
            if (split.length != 2) {
                throw new IllegalArgumentException("Not a range: " + str);
            }
            try {
                final int first = Integer.parseInt(split[0]);
                final int second = Integer.parseInt(split[1]);
                if (first > second) {
                    throw new IllegalArgumentException("a must be <= b: " + str);
                }
                return new Interval(first, second);
            } catch (final NumberFormatException e) {
                throw new IllegalArgumentException("Malformed range: " + str);
            }
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public int compareTo(@Nonnull final Interval o2) {
            return COMPARATOR.compare(this, o2);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Interval)) {
                return false;
            }
            Interval interval = (Interval) obj;
            return first == interval.first
                    && second == interval.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    /** Enum of known methods to be called */
    private enum Method {
        STATS,
        FIBONACCI,
        OVERLAP;

        static Optional<Method> fromString(final String str) {
            return Optional.ofNullable(METHOD_MAPPER.get(str));
        }
    }
}
