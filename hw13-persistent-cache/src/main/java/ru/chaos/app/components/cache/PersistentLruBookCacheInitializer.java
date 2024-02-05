package ru.chaos.app.components.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.chaos.app.entities.Book;
import ru.chaos.app.repositories.BooksRepository;

import java.util.List;

@Component
public class PersistentLruBookCacheInitializer implements CacheInitializer<Long, Book> {
    BooksRepository booksRepository;
    Cache<Long, Book> persistentLruCache;
    @Value("${cache.capacity:1000}")
    private int capacity;
    @Value("${cache.data-loss-interval:30}")
    private int dataLossInterval;


    @Autowired
    public PersistentLruBookCacheInitializer(BooksRepository booksRepository, Cache<Long, Book> persistentLruCache) {
        this.booksRepository = booksRepository;
        this.persistentLruCache = persistentLruCache;
    }

    @Override
    public Cache<Long, Book> initialize() {
        if (capacity < 1) {
            throw new IllegalStateException("Cache capacity cannot be less than 1.");
        }
        List<Book> retrievedBooks = booksRepository.findLastCacheBooks(capacity, dataLossInterval);
        for (Book book : retrievedBooks) {
            persistentLruCache.add(book.getId(), book);
        }
        return persistentLruCache;
    }
}
