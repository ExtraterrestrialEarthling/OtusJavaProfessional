package ru.chaos.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chaos.app.dtos.BookDto;
import ru.chaos.app.entities.Book;
import ru.chaos.app.services.BooksService;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable(name = "id") Long id) {
        Book book = booksService.getBookById(id);
        return new BookDto(book.getId(), book.getTitle());
    }
}

