package org.russell.algorithms.practice.unsorted;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Objects;

public class LRU<K, V> {

    final LinkedHashMap<K, V> cache;
    
    final int maxSize;
    
    public LRU(final int maxSize) {
        this.maxSize = maxSize;
        cache = new LinkedHashMap<K, V>(maxSize);
    }

    public V get(K key) {
        Objects.requireNonNull(key);
        
        // element in cache
        final V value = cache.remove(key);
        if (value != null) {
            // remove element and put it at the end
            cache.put(key, value);
            return value;
        }
        
        return value;
    }
    
    /**
     * Returns old value of the key
     * @param key
     * @param value
     * @return
     */
    public void set(K key, V value) {
        final V oldValue = get(key);

        // limit not reached || key exists
        if (cache.size() < maxSize || oldValue != null) {
            // add value to the end
            cache.put(key, value);
            return;
        }
            
        // Limit reached, remove first (LRU) and add new value
        final Iterator<Entry<K, V>> it = cache.entrySet().iterator();
        it.next();
        it.remove();
        cache.put(key, value);
    }
    
    
    @Override
    public String toString() {
        return cache.toString();
    }
    
    public static void main(String[] args) {
        
        LRU<Integer, Integer> lru = new LRU<>(6);
        lru.set(4, 4);
        System.out.println(lru);
        lru.set(5, 5);
        System.out.println(lru);
        lru.set(6, 6);
        System.out.println(lru);
        lru.set(4, 44);
        System.out.println(lru);
        lru.set(7, 7);
        System.out.println(lru);
        lru.set(8, 8);
        System.out.println(lru);
        lru.set(4, 4);
        System.out.println(lru);
        lru.set(9, 9);
        System.out.println(lru);
        lru.set(10, 10);
        System.out.println(lru);
        lru.set(5, 5);
        System.out.println(lru);
        lru.get(4);
        System.out.println(lru);
    }
}
