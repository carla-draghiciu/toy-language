package model.adt;

import java.util.Map;

public interface MyIDictionary<K, T> {
    void add(K key, T value);
    T getElement(K key);
    boolean contains(K key);
    void remove(K key);
    Iterable<Map.Entry<K,T>> entrySet();
    void putAll(MyDictionary<K,T> dictionary);
}
