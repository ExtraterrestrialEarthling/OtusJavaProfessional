package ru.chaos.app.services;

import ru.chaos.app.dtos.BookDto;
import ru.chaos.app.entities.Book;

public interface BooksService {
    Book getBookById(Long id);
}
