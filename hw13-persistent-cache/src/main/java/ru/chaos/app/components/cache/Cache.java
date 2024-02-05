package ru.chaos.app.components.cache;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.Map;

public interface Cache<K, V> {
    boolean exists(K key);
    V get(K key);
    void add(K key, V value);
    int size();
}
