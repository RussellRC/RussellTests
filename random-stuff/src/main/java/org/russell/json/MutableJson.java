package org.russell.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.AutoPopulatingList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MutableJson {

    public static void main(String[] args) throws Exception {
        MutableJson json = new MutableJson();

        System.out.println("Before:\t" + json.map());

        json.update("$.store.name", "Book Store");
        json.update("$.store.books[0].isbn", "444");
        json.update("$.store.books[0].name", "four four four");
        json.update("$.store.books[1].isbn", "555");
        json.update("$.store.books[2]", Arrays.asList(1, 2, 3));

        final ObjectMapper mapper = new ObjectMapper();
        System.out.println("After:\t" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json.map()));
    }

    private final Map<String, Object> json;

    public MutableJson() {
        json = new LinkedHashMap<String, Object>();
    }

    public MutableJson(Map<String, Object> json) {
        this.json = json;
    }

    public Map<String, Object> map() {
        return json;
    }

    public void update(String path, Object newValue) {
        updateJson(this.json, Path.parse(path), newValue);
    }

    @SuppressWarnings("unchecked")
    private void updateJson(Map<String, Object> data, Iterator<Token> path, Object newValue) {

        while (path.hasNext()) {
            Token token = path.next();

            if (token.value(data) == null) {
                token.create(data, path);
            }

            if (path.hasNext()) {
                Object value = token.value(data);
                if (value instanceof Map) {
                    updateJson((Map<String, Object>) value, path, newValue);
                }
            } else {
                token.update(data, newValue);
            }
        }

    }
}

class Path {
    public static Iterator<Token> parse(String path) {
        if (path.isEmpty()) {
            return Collections.<Token> emptyList().iterator();
        }
        if (path.startsWith("$.")) {
            path = path.substring(2);
        }

        List<Token> tokens = new ArrayList<>();
        for (String part : path.split("\\.")) {
            if (part.matches("\\w+\\[\\d+\\]")) {
                String fieldName = part.substring(0, part.indexOf('['));
                int index = Integer.parseInt(part.substring(part.indexOf('[') + 1, part.indexOf(']')));
                tokens.add(new ArrayToken(fieldName, index));
            } else {
                tokens.add(new FieldToken(part));
            }
        }
        ;

        return tokens.iterator();
    }
}

abstract class Token {

    protected final String fieldName;

    Token(String fieldName) {
        this.fieldName = fieldName;
    }

    public abstract Object value(Map<String, Object> data);

    public abstract void update(Map<String, Object> data, Object newValue);

    public abstract void create(Map<String, Object> data, Iterator<Token> path);

}

class FieldToken extends Token {

    FieldToken(String fieldName) {
        super(fieldName);
    }

    @Override
    public Object value(Map<String, Object> data) {
        return data.get(fieldName);
    }

    @Override
    public void update(Map<String, Object> data, Object newValue) {
        data.put(fieldName, newValue);
    }

    @Override
    public void create(Map<String, Object> data, Iterator<Token> path) {
        if (path != null && path.hasNext()) {
            data.put(fieldName, new LinkedHashMap<String, Object>());
        } else {
            data.put(fieldName, null);
        }
    }
}

class ArrayToken extends Token {

    private final int index;

    ArrayToken(String fieldName, int index) {
        super(fieldName);
        this.index = index;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object value(Map<String, Object> data) {
        List<Object> list = (List<Object>) data.get(fieldName);
        if (list == null) {
            return null;
        }
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(Map<String, Object> data, Object newValue) {
        List<Object> list = (List<Object>) data.get(fieldName);
        list.set(index, newValue);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void create(Map<String, Object> data, Iterator<Token> path) {
        List<Object> list = (List<Object>) data.get(fieldName);
        if (list == null) {
            list = new AutoPopulatingList<Object>((index) -> {
                return new LinkedHashMap<String, Object>();
            });
            data.put(fieldName, list);
        }
        if (path != null && path.hasNext()) {
            list.get(index);
        }
    }
}
