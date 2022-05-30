package org.russell.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RussellStreamTests {

    public static void main(String[] args) {
        filter();
    }
    
    public static void filter() {
        
        final List<String> list = Arrays.asList("a", "b", "c");
        final List<String> listOfA = list.stream()
                .filter("a"::equals)
                .collect(Collectors.toList());
        System.out.println(listOfA);
        
        final int[] nums = {1, 2, 3, 4, 5};
        System.out.println(Arrays.stream(nums).reduce(0, Integer::sum));
        
        
    }

    private static void grouping() {
        List<MyFlatObject> list = new ArrayList<>();
        list.add(MyFlatObject.of("key1", "all", "ww", "common", "en", "hi"));

        list.add(MyFlatObject.of("key1", "all", "amr", "common", "en", "Hello"));
        list.add(MyFlatObject.of("key1", "all", "amr", "common", "es", "Hola"));

        list.add(MyFlatObject.of("key1", "all", "emea", "common", "fr", "Bonjour"));
        list.add(MyFlatObject.of("key1", "all", "emea", "common", "it", "Bonjorno"));

        list.add(MyFlatObject.of("key2", "all", "ww", "common", "en", "hi 2"));
        list.add(MyFlatObject.of("key2", "all", "ww", "common", "es", "hola 2"));
        list.add(MyFlatObject.of("key2", "all", "ww", "common", "fr", "Bonjour 2"));

        list.add(MyFlatObject.of("key2", "all", "ema", "common", "it", "Bonjorno 2"));

        
        Map<String, Set<MarketingContext>> collect = list.stream()
                                                         .collect(Collectors.groupingBy(MyFlatObject::getKeyPath,
                                                                                        Collectors.mapping(mapper(),
                                                                                                           Collectors.toSet())));
        
        final List<MyCAC> collect2 = list.stream().map(mapper2()).collect(Collectors.toList());
        final Map<String, List<MyCAC>> collect3 = collect2.stream().collect(Collectors.groupingBy(MyCAC::getKeyPath));
        
        // CACs grouped by keyPath
        final Map<String, Collection<MyCAC>> finalResult = new LinkedHashMap<>();
        
        for (final Entry<String, List<MyCAC>> entry3 : collect3.entrySet()) {
            
            // One object per Marketing Context
            final Map<MarketingContext, Optional<MyCAC>> collect4 = entry3.getValue()
                                                                         .stream()
                                                                         .collect(Collectors.groupingBy(MyCAC::getMarketingContext,
                                                                                                        Collectors.reducing(mergeMyCACs())));
            // All the CAC per keyPath
            final List<MyCAC> cacForKeypath = collect4.values().stream().flatMap(Optional::stream).collect(Collectors.toList());
            finalResult.put(entry3.getKey(), cacForKeypath);
            
        }
        
        finalResult.forEach((k, v) -> {System.out.println(k + ": "); System.out.println("   " + v);});
    }

    private static BinaryOperator<MyCAC> mergeMyCACs() {
        return new BinaryOperator<MyCAC>() {

            @Override
            public MyCAC apply(MyCAC t, MyCAC u) {
                assert t.getKeyPath().equals(u.getKeyPath());
                assert t.getMarketingContext().equals(u.getMarketingContext());
                
                final Map<String, String> translations = new LinkedHashMap<>();
                translations.putAll(t.translations);
                translations.putAll(u.translations);
                
                final MyCAC merge = new MyCAC();
                merge.keyPath = t.getKeyPath();
                merge.marketingContext = t.getMarketingContext();
                merge.translations = translations;
                return merge;
            }
            
        };
    }
    
    private static Function<MyFlatObject, MyCAC> mapper2() {
        return new Function<MyFlatObject, MyCAC> () {

            @Override
            public MyCAC apply(MyFlatObject t) {
                final MyCAC cac = new MyCAC();
                cac.keyPath = t.getKeyPath();
                cac.marketingContext = MarketingContext.newInstance(t.segment, t.geo, t.format);
                cac.translations = Collections.singletonMap(t.getLangauge(), t.getValue());
                return cac;
            }
            
        };
    }

    private static Function<MyFlatObject, MarketingContext> mapper() {
        return (t) -> {
            return MarketingContext.newInstance(t.segment, t.geo, t.format);
        };
    }
}
