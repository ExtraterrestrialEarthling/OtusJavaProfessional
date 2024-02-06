package ru.chaos.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chaos.app.components.cache.Cache;
import ru.chaos.app.components.cache.CacheInitializer;
import ru.chaos.app.entities.Book;
import ru.chaos.app.repositories.BooksRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class BooksServiceImpl implements BooksService {
    private BooksRepository booksRepository;
    private Cache<Long, Book> cache;
    private Logger logger;


    @Autowired
    public BooksServiceImpl(BooksRepository booksRepository, CacheInitializer<Long, Book> cacheInitializer) {
        logger = LoggerFactory.getLogger(BooksServiceImpl.class);
        this.booksRepository = booksRepository;
        this.cache = cacheInitializer.initialize();
        if (cache.size() == 0) {
            logger.info("No cache items found in database, initializing an empty cache");
        } else {
            logger.info("Restored items are put in cache");
        }
    }

    public Book getBookById(Long id) {
        Book book;
        book = cache.get(id);
        if (book == null) {
            logger.info(String.format("Searching for the book with id %d in database", id));
            book = booksRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found in database"));
            logger.info("Book found: " + book.toString());
            cache.add(id, book);
        }
        updateLastAccessTime(book);
        return book;
    }


    private void updateLastAccessTime(Book book) {
        book.setLastAccessTime(LocalDateTime.now());
        booksRepository.save(book);
    }
}
