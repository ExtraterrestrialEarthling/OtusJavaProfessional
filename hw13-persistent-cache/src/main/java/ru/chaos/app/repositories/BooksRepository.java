package ru.chaos.app.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import ru.chaos.app.entities.Book;

import java.util.List;


public interface BooksRepository extends ListCrudRepository<Book, Long> {

    @Query("SELECT * FROM books " +
            "WHERE last_access_time >= CURRENT_TIMESTAMP - :dataLossInterval SECOND " +
            "ORDER BY last_access_time DESC " +
            "LIMIT :numOfItems")
    List<Book> findLastCacheBooks(@Param("numOfItems") int numOfItems,
                                          @Param("dataLossInterval") long dataLossInterval);
}

