package org.example.lruCache;


import java.util.LinkedHashMap;
import java.util.Map;

class LinkedHashmapBuiltIn<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

    public LinkedHashmapBuiltIn(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

public class LinkedHashmapLRU {

    public static void main(String[] args) {
        LinkedHashmapBuiltIn<Integer, String> cache = new LinkedHashmapBuiltIn<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        System.out.println(cache.get(2)); // "Two"

        cache.put(4, "Four"); // evicts key 1
        System.out.println(cache.get(1)); // null
        System.out.println(cache.get(3)); // "Three"
    }

}
