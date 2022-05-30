package org.russell.annotations;

import java.util.Map;

import org.russell.annotations.annotation.AnnotationMap;
import org.russell.annotations.annotation.AnnotationProperty;
import org.russell.annotations.annotation.NestedAnnotationProperty;


public class TextAsset {

    @AnnotationProperty("keypath")
    protected String keyPath;
    
    @AnnotationProperty("description")
    protected String description;
    
    @AnnotationProperty("fragmentType")
    protected String assetType;
    
    @NestedAnnotationProperty
    protected MarketingContext mc;
    
    @AnnotationMap(keyProperty="languageCode", valueProperty="value")
    protected Map<String, String> locales;
    
    /**
     * 
     * @author russellrazo
     *
     */
    public static class MarketingContext {
        
        @AnnotationProperty("segmentCode")
        public String segment;
        @AnnotationProperty("geoCode")
        public String geo;
        @AnnotationProperty("formatCode")
        public String format;
    }
}
