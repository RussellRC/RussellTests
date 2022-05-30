package org.russell.algorithms;

import java.util.LinkedHashMap;
import java.util.List;

public class Dummy {

    public static void main(String[] args) {
        LinkedHashMap<String, List<Integer>> map = new LinkedHashMap<>();
        map.put("x", null);

        for (Integer num : map.get("x")) {
            System.out.println(num);
        }
    }


}
