package org.russell.util;

public class Dummy {

    public static void main(String[] args) {
        "word".chars().mapToObj(n -> (char) n).forEach(System.out::println);
    }


}
