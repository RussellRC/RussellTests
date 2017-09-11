package russell.tests.algorithms.domain;

import russell.tests.algorithms.domain.Trie;

public class TrieTest {
    static {
        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "warn");
    }
    
    public static void main(String[] args) {
        
        final Trie trie = new Trie();
        System.out.println(trie.insert("hello"));
        System.out.println(trie.insert("holland"));
        System.out.println(trie.insert("hollie"));
        
        System.out.println(trie.insert("holland"));
        
        System.out.println(trie.contains("hell"));
    }
}
