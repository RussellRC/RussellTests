package russell.tests.annotations;

import java.util.Map;

import russell.tests.annotations.annotation.AnnotationMap;
import russell.tests.annotations.annotation.AnnotationProperty;
import russell.tests.annotations.annotation.NestedAnnotationProperty;


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
