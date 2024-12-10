package ru.edu.springweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.edu.springweb.model.domain.Book;
import ru.edu.springweb.model.dto.BookDto;
import ru.edu.springweb.service.BookService;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                conversionService.convert
                        (bookService.getBook(id),
                                BookDto.class)
        );
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        return ResponseEntity.ok(
                bookService.getBooks()
                        .stream()
                        .map(book -> conversionService.convert(book, BookDto.class))
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<Object> addBook(@RequestBody BookDto dto) {
        bookService.addBook(conversionService.convert(dto, Book.class));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody BookDto dto) {
        bookService.updateBook(id, conversionService.convert(dto, Book.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
