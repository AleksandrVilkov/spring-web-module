package ru.edu.springweb.service;

import ru.edu.springweb.model.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();

    Book getBook(int id);

    void addBook(Book book);

    void updateBook(int id, Book book);

    void deleteBook(int id);
}
