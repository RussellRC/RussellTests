package russell.tests;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class CSCASDiff {

    
    public static void main(String[] args) {
        
        final String s1 = "\"nodePath\"";
        final String s2 = "\"widgetName\"";
        final String s3 = "\"mobileTemplate\"";
        
        final Set<String> cs = readSet("cs_catNodesDesc.plist", s3);
        final Set<String> cas = readSet("cas_catNodesDesc.plist", s3);
        
        for (String s: cs) {
            System.out.println(s);
        }
        System.out.println("=================");
        for (String s: cas) {
            System.out.println(s);
        }
        System.out.println("=================");
        
        for (String s : cs) {
            if (!cas.contains(s)) {
                System.out.println(s);
            }
        }
    }
    
    public static Set<String> readSet(final String fileName, final String startStr) {
        final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        final String str = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
        
        String[] lines = str.split(System.getProperty("line.separator"));
        final Set<String> set = new TreeSet<>();
        
        for (String s : lines) {
            final String trim = s.trim();
            if (trim.startsWith(startStr) && !trim.contains("test")) {
                if (set.add(trim)) {
                    
                }
            }
        }
        
        return set;
    }
}
