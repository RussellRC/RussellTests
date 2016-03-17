package russell.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.rowset.serial.SerialBlob;


public class AOS {

    
    public static void main(String[] args) throws Exception {
        
        final String path = "~/Developer/svn/mobile/internal/TRUNK/stprojects/iphone6s-step1-overlay-upgrade-201511.stproj/data/data.xml".replaceFirst("^~",
                                                                                                                                                       System.getProperty("user.home"));
        final File f = new File(path);
        final Scanner s = new Scanner(f);
        s.useDelimiter("\\A");
        
        String content = s.next();
        byte[] encode = Base64.getEncoder().encode(content.getBytes());
        System.out.println(new String(encode));
        
        byte[] encodeBase64 = org.apache.commons.codec.binary.Base64.encodeBase64(content.getBytes());
        System.out.println(new String(encodeBase64));
        
    }
    
    void doCSCASDiff() {
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
