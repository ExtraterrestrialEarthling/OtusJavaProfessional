package ru.chaos.app.components.cache;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.ref.SoftReference;
import java.util.*;

@Component
public class PersistentLruCache<K, V> implements Cache<K,V> {
    private final Map<K, SoftReference<V>> cacheItems;
    private final Logger logger;
    @Getter
    @Value("${cache.data-loss-interval:1000}")
    private int cacheDataLossInterval;
    @Getter
    @Value("${cache.capacity:30}")
    private int capacity;


    @Autowired
    public PersistentLruCache() {
        logger = LoggerFactory.getLogger(PersistentLruCache.class);
        cacheItems = new LinkedHashMap<>();
    }

    public synchronized boolean exists(K key) {
        boolean status;
        if(cacheItems.get(key)==null){
            status = false;
        } else {
            SoftReference<V> softValue = cacheItems.get(key);
            status = softValue != null && softValue.get() != null;
        }
        logger.info("Checking if cache item exists in cache - " + status);
        return status;
    }

    public synchronized V get(K key) {
        if(!exists(key)){
            return null;
        }
        SoftReference<V> softValue = cacheItems.remove(key);
        V item = softValue == null ? null : softValue.get();
        if (item == null) {
            logger.warn("Garbage collector deleted requested cache item");
            return null;
        }
        cacheItems.put(key, softValue);
        logger.info("Retrieving item from cache");
        return item;
    }

    public synchronized void add(K key, V value) {
        if (exists(key)) {
            putToCache(key, value);
        } else {
            if (cacheItems.size() == capacity) {
                removeOldestItem();
            }
            putToCache(key, value);
        }
    }

    public int size(){
        return cacheItems.size();
    }

    private void removeOldestItem() {
        cacheItems.remove(cacheItems.keySet().iterator().next());
        logger.info("Oldest cache item evicted");
    }

    private void putToCache(K key, V cacheItem){
        cacheItems.put(key, new SoftReference<>(cacheItem));
        logger.info("Item saved to cache");
    }
}
