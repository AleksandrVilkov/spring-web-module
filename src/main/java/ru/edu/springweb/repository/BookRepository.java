package ru.edu.springweb.repository;

import ru.edu.springweb.model.domain.Book;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(int id);

    void save(Book newBook) throws RuntimeException;

    void delete(int id) throws NoSuchElementException;

    void update(Book book) throws NoSuchElementException;
}
