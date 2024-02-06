package ru.chaos.app.components.cache;

public interface CacheInitializer<K, V> {
    Cache<K,V> initialize();
}
