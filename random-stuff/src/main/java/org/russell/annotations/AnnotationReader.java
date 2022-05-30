package org.russell.annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.russell.annotations.TextAsset.MarketingContext;
import org.russell.annotations.annotation.AnnotationMap;
import org.russell.annotations.annotation.AnnotationProperty;
import org.russell.annotations.annotation.NestedAnnotationProperty;


public class AnnotationReader {

    
    public static void main(String[] args) throws Exception {
        
        TextAsset ta1 = new TextAsset();
        ta1.keyPath = "my.text.asset.1";
        ta1.description = "text asset 1 descriptio";
        ta1.mc = new MarketingContext();
        ta1.mc.segment = "all";
        ta1.mc.geo = "ww";
        ta1.mc.format = "common";
        ta1.locales = new LinkedHashMap<String, String>();
        ta1.locales.put("en", "hello");
        ta1.locales.put("es", "hola");
        
        //printAnnotation(ta1);
        System.out.println("+++++");
        
        Fragment f1 = new Fragment();
        f1.keyPath = "my.fragment.asset.1";
        f1.description = "fragment 1 description";
        f1.assetType = "astro";
        f1.mc = new MarketingContext();
        f1.mc.segment = "all";
        f1.mc.geo = "ww";
        f1.mc.format = "common";
        f1.locales = new LinkedHashMap<String, String>();
        f1.locales.put("en", "hello f");
        f1.locales.put("es", "hola f");
        System.out.println();
        printAnnotation(f1);
        
        System.out.println();
        System.out.println("=== end ===");
    }
    
    private static List<Field> getAllFieldsHelper(List<Field> fields, final Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFieldsHelper(fields, type.getSuperclass());
        }
        return fields;
    }
    
    public static List<Field> getAllFields(final Class<?> type) {
        return getAllFieldsHelper(new ArrayList<Field>(), type);
    }
    
    public static void printAnnotation(Object obj) throws Exception {
        if (obj == null) {
            return;
        }
        
        for (Field field : getAllFields(obj.getClass())) {
            if (field.isAnnotationPresent(AnnotationProperty.class)) {
                final AnnotationProperty annotation = field.getAnnotation(AnnotationProperty.class);
                final Object fieldValue = field.get(obj);
                if (fieldValue == null && annotation.ignoreNull()) {
                    continue;
                }
                System.out.println(annotation.value() + "=" + fieldValue);
            } else if (field.isAnnotationPresent(NestedAnnotationProperty.class)) {
                final Object nestedObject = field.get(obj);
                printAnnotation(nestedObject);
            } else if (field.isAnnotationPresent(AnnotationMap.class)) {
                final Object fieldObject = field.get(obj);
                if (!(fieldObject instanceof Map)) {
                    continue;
                }
                final Map<?, ?> map = (Map<?, ?>) field.get(obj);
                final AnnotationMap annotation = field.getAnnotation(AnnotationMap.class);
                for (final Object key : map.keySet()) {
                    if (key == null || map.get(key) == null) {
                        continue;
                    }
                    System.out.println(annotation.keyProperty() + "=" + String.valueOf(key));
                    System.out.println(annotation.valueProperty() + "=" + map.get(key));
                }
            }
        }
    }
}
