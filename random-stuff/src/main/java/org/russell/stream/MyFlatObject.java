package org.russell.stream;

public class MyFlatObject {

    String keyPath;
    String segment;
    String geo;
    String format;
    String langauge;
    String value;

    public static MyFlatObject of(String keyPath, String segment, String geo, String format,
                                           String language, String value) {
        MyFlatObject myObject = new MyFlatObject();
        myObject.keyPath = keyPath;
        myObject.segment = segment;
        myObject.geo = geo;
        myObject.format = format;
        myObject.langauge = language;
        myObject.value = value;
        return myObject;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public String getSegment() {
        return segment;
    }

    public String getGeo() {
        return geo;
    }

    public String getFormat() {
        return format;
    }

    public String getLangauge() {
        return langauge;
    }

    public String getValue() {
        return value;
    }
}
