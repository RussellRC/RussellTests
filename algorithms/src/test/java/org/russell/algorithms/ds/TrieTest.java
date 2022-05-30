package org.russell.algorithms.ds;

import org.russell.algorithms.ds.Trie;

public class TrieTest {
    
    public static void main(String[] args) {
        
        final Trie trie = new Trie();
        System.out.println(trie.insert("hello"));
        System.out.println(trie.insert("holland"));
        System.out.println(trie.insert("hollie"));
        
        System.out.println(trie.insert("holland"));
        
        System.out.println(trie.contains("hell"));
    }
}
