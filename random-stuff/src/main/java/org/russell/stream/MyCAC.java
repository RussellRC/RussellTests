package org.russell.stream;

import java.util.LinkedHashMap;
import java.util.Map;

class MyCAC {

    String keyPath;
    MarketingContext marketingContext = new MarketingContext();
    Map<String, String> translations = new LinkedHashMap<>();

    public String getKeyPath() {
        return keyPath;
    }

    public MarketingContext getMarketingContext() {
        return marketingContext;
    }

    @Override
    public String toString() {
        return String.format("{%s | %s | %s}", keyPath, marketingContext, translations);
    }
}
