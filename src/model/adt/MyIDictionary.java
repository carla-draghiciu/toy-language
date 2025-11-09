package model.adt;

public interface MyIDictionary<K, T> {
    void add(K key, T value);
    T getElement(K key);
    boolean contains(K key);
    void remove(K key);
}
