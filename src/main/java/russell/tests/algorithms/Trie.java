package russell.tests.algorithms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Trie {

    private static final Logger log = LoggerFactory.getLogger(Trie.class);
    
    static class Node {
        final Map<Character, Node> children = new HashMap<>();
        final String value;
        
        Node(final String value) {
            this.value = value;
        }
        
        @Override
        public String toString() {
            return value;
        }
    }
    
    /** Root node */
    private final Node root = new Node("ROOT");
    
    /**
     * Inserts 
     * @param element
     * @return {@code true} if this collection changed as a result of the call
     */
    public boolean insert(final String element) {
        
        boolean trieChanged = false;
        
        int i = 0;
        Node node = root;
        final StringBuilder newValue = new StringBuilder();
        while (i < element.length() && node.children.containsKey(element.charAt(i))) {
            node = node.children.get(element.charAt(i));
            newValue.append(element.charAt(i));
            i++;
        }
        
        if (i < element.length()) {
            trieChanged = true;
        }
        
        // append new nodes
        while (i < element.length()) {
            newValue.append(element.charAt(i));
            final Node newNode = new Node(newValue.toString());
            node.children.put(element.charAt(i), newNode);
            
            log.debug("Current node: " + node.value);
            log.debug(String.format("inserting child: {%s=%s}", element.charAt(i), newValue));
            
            node = newNode;
            i++;
        }
        
        return trieChanged;
    }
    
    /**
     * Contains
     * @param element
     * @return
     */
    public boolean contains(final String element) {
        Node node = root;
        int i = 0;
        while (i < element.length() && node != null) {
            final Character key = element.charAt(i);
            log.debug("Current node: " + node.value);
            log.debug(String.format("Searching for key '%s'", key));
            node = node.children.get(key);
            i++;
        }
        
        return node != null && node.value.equals(element);
    }
}
