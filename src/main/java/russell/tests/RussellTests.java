package russell.tests;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;
import org.apache.commons.lang3.text.translate.LookupTranslator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;


public class RussellTests {
    
    final static ObjectMapper mapper = new ObjectMapper();
    LinkedList<String> ll;
    
    public static void main(String[] args) {

    	int[] prices = {100, 90, 80, 70, 60};
    	int profit = 0;
        int minElement = Integer.MAX_VALUE;
        for(int i=0; i<prices.length; i++){
           profit = Math.max(profit, prices[i]-minElement);
           minElement = Math.min(minElement, prices[i]);
        }
        System.out.println(profit);
        
        //Facebook fb = new FacebookTemplate("");
        
//        System.out.println(Long.MAX_VALUE);
//        long i = Long.MAX_VALUE;
//        while (i*10 < 0) {
//            i = i/10;
//        }
//        System.out.println(i);
        
//        System.out.println();
//        System.out.println(Long.MAX_VALUE);
//        for (int i = 0; i < 10; i++) {
//            System.out.println((Long.MAX_VALUE - i) * 10);
//        }
//        
//        System.out.println();
//        for (int i = 1; i < 10; i++) {
//            System.out.println(Long.MAX_VALUE + i);
//        }
        

    }
    
    public static void testParseLong() {
        System.out.println(Long.MIN_VALUE);
        System.out.println(parseLong(String.valueOf(Long.MIN_VALUE)));
        System.out.println("===");
        System.out.println(Long.MAX_VALUE);
        System.out.println(parseLong(String.valueOf(Long.MAX_VALUE)));
        System.out.println("===");
        
        Random r = new Random();
        int i = 10000;
        while (i > 0) {
            final long nextLong = r.nextLong();
            if (nextLong != parseLong(String.valueOf(nextLong))) {
                System.out.println("ERROR!!!  " + nextLong);
            }
            i--;
        }
    }
    
    public static long parseLong(final String s) {
        final boolean isNegative = s.charAt(0) == '-';
        int index = isNegative ? 1 : 0;
        
        long result = 0;
        while (index < s.length()) {
            if (result * 10 > 0) {
                throw new NumberFormatException("Number out of range");
            }
            result = result * 10;
            final int digit = Character.getNumericValue(s.charAt(index));
            if (result - digit > 0) {
                throw new NumberFormatException("Number out of range"); 
            }
            result = result - digit;
            
            index++;
        }
        
        if (!isNegative && -result < 0) {
            // End up in positive MIN_VALUE
            throw new NumberFormatException("Number out of range");
        }
        
        return isNegative ? result : -result;
    }
        
    
    
    public static void filter() {
        final List<String> l1 = Arrays.asList("1", "2", "3");
        final List<String> l2 = Collections.emptyList();
        
        final List<List<String>> lists = Arrays.asList(l1, l2);
        
        final List<List<String>> emptyLists = lists.stream().filter(l -> l.isEmpty()).collect(Collectors.toList());
        final List<List<String>> nonEmptyLists = lists.stream().filter(l -> !(l.isEmpty())).collect(Collectors.toList());
        
        System.out.println("filter l.isEmpty():" + emptyLists);
        System.out.println("filter l.isNotEmpty():" + nonEmptyLists);
        
        System.out.println(emptyLists.size());
    }
    
    public static boolean isRoot(Map<String, String> m) {
        return m.get("s").equals("all") && m.get("g").equals("ww") && m.get("f").equals("common");
    }
    
    
    public static void jsonPath() throws Exception {
        final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jsonPathTest.json");
        final String json = new Scanner(in,"UTF-8").useDelimiter("\\A").next();
        
        List<String> authors = JsonPath.read(json, "$.store.book[*].author");
        //System.out.println(authors);
        
        
        
        final JsonNode rootNode = mapper.readValue(json, JsonNode.class);
        printFieldKeys(rootNode, "$");
        
    }

    private static void printFieldKeys(JsonNode rootNode, String parent) {
        final Iterator<Entry<String, JsonNode>> fieldIt = rootNode.fields();
        while (fieldIt.hasNext()) {
            final Entry<String, JsonNode> next = fieldIt.next();
            final JsonNode value = next.getValue();
            final String path = parent + "." + next.getKey();
            
            if (value.isValueNode()) {
                System.out.println(path + " = " + value.asText());
            } else  {
                System.out.println(path);
            }
            

            if (value.isArray()) {
                for (int i = 0; i < value.size(); i++) {
                    printFieldKeys(value.get(i), path + "[" + i + "]");
                }
            } else {
                printFieldKeys(value, path);
            }

        }
    }

    public static void md5() throws Exception {

        final MessageDigest md = MessageDigest.getInstance("MD5");
        for (int i = 0; i < 10; i++) {
            System.out.println(DigestUtils.md5Hex("russell".getBytes(StandardCharsets.UTF_8)));
            Thread.sleep(RandomUtils.nextInt(1000, 5000));
        }
    }

    public static void zonedDateTime() {

        LocalDateTime dt = LocalDateTime.now();
        ZonedDateTime zdt = dt.atZone(ZoneId.of("UTC"));
        System.out.println(zdt.toString());

    }

    public static final CharSequenceTranslator ESCAPE_SOLR = new LookupTranslator(new String[][] { { "+", "\\+" },
                                                                                                  { "-", "\\-" },
                                                                                                  { "&&", "\\&&" },
                                                                                                  { "||", "\\||" },
                                                                                                  { "!", "\\!" },
                                                                                                  { "(", "\\(" },
                                                                                                  { ")", "\\)" },
                                                                                                  { "{", "\\{" },
                                                                                                  { "}", "\\}" },
                                                                                                  { "[", "\\[" },
                                                                                                  { "]", "\\]" },
                                                                                                  { "^", "\\^" },
                                                                                                  { "\"", "\\\"" },
                                                                                                  { "~", "\\~" },
                                                                                                  { "*", "\\*" },
                                                                                                  { "?", "\\?" },
                                                                                                  { ":", "\\:" },
                                                                                                  { "\\", "\\\\" }, });

    static void solrEscape() {

        String s = "some solr query +";
        System.out.println(s);
        System.out.println(ESCAPE_SOLR.translate(s));

        s = "\\ solr ^ \" query ~";
        System.out.println(s);
        System.out.println(ESCAPE_SOLR.translate(s));

        s = null;
        System.out.println(s);
        System.out.println(ESCAPE_SOLR.translate(s));
    }

    static void csv() throws Exception {
        String s, translate;

        // s =
        // "\"\"\"double quoted cell with trailing spaces\"\"   \",\" \"\"double quoted cell with leading space\"\"\"";

        s = "\"comma,\"\"separated\"\",cell1\"" + "," + "\"comma,separated,cell2\"";
        System.out.println(s);

        CSVReader csvr = new CSVReader(new StringReader(s));
        String[] arr = csvr.readNext();
        System.out.println(Arrays.toString(arr));

        CSVParser parser = new CSVParser();
        arr = parser.parseLine(s);
        System.out.println(Arrays.toString(arr));
    }

    private static void isoDate() {
        DateTime dt = new DateTime();
        System.out.println("DateTime millis: " + dt.getMillis());

        DateTimeFormatter fmt = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();
        DateTimeFormatter fmt2 = ISODateTimeFormat.dateTime().withZoneUTC();

        String s = fmt.print(dt);

        System.out.println("Formatted DateTime: " + s);

        DateTime parsedDTNoMillis = fmt.parseDateTime(s);
        DateTime parsedDT = fmt2.parseDateTime(s);
        System.out.println("Parsed DateTime millis: " + parsedDTNoMillis.getMillis());
        // System.out.println("Parsed DateTime millis: " + parsedDT.getMillis());

        Date date = parsedDT.toDate();
        System.out.println("Parsed Date millis: " + date.getTime());
        // fmt.withZoneUTC().
    }

    public static void regex() {
        final Pattern p = Pattern.compile("text/csv|text/xml|application/zip");

        Matcher m = p.matcher("text/xml");
        System.out.println(m.matches());
        System.out.println(m.group());

    }

    public static void utcDate() {
        System.out.println("user.timezone:" + System.getProperty("user.timezone"));
        System.out.println("default timezone: " + TimeZone.getDefault().getID());
        // System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));

        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        fmt.setTimeZone(TimeZone.getTimeZone("utc"));

        System.out.println(fmt.format(new Date(System.currentTimeMillis())));
    }

    public static void dateformat() throws Exception {
        // "2014-01-20T13:42:26Z"
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        System.out.println(fmt.parse("2014-01-20T13:42:26Z").getTime());
    }

    public static void spaces() {
        String regex = "\\s*(\\s|,|\\r|\\n)\\s*";

        String keysToSearch = ",some text, with,commas , and \r\n spaces\rand lines\nand \n\rthings,,\r\n\n\r  ,";
        System.out.println(keysToSearch);
        final String string = Arrays.stream(keysToSearch.split(regex)).filter(e -> !e.trim().isEmpty()).map(func())
                                    .collect(Collectors.joining(","));

        System.out.println("+++");
        System.out.println(string);
    }

    private static Function<String, String> func() {
        return new Function<String, String>() {

            @Override
            public String apply(String t) {
                if (!t.endsWith("*")) {
                    return t.concat("*");
                }
                return t;
            }
        };
    }

}
