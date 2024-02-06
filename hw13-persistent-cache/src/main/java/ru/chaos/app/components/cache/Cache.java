package ru.chaos.app.components.cache;

public interface Cache<K, V> {
    boolean exists(K key);
    V get(K key);
    void add(K key, V value);
    int size();
}
