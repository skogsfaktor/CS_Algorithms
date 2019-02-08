package Algorithms;

import java.util.LinkedList;

public class HashishMap<K, V> {
    private class KeyValuePair {
        K key;
        V value;
    }

    private LinkedList<V>[] buckets;

    public void Hashish(int size) {
        buckets = (LinkedList<V>[])new LinkedList[size];
    }

    public void put(K key, V value) {
        buckets[key.hashCode() % buckets.length].add(value);
    }

    public V get(K key) {
        return buckets[key.hashCode() % buckets.length].getFirst();
    }

    public void delete(K key) {
        
    }
}
