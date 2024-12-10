package ru.edu.springweb.repository;

import org.springframework.stereotype.Repository;
import ru.edu.springweb.model.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final List<Book> books = new ArrayList<>();

    public BookRepositoryImpl() {
        var book1 = new Book();
        book1.setId(1);
        book1.setTitle("Чистый код");
        book1.setAuthor("Роберт Мартин");

        var book2 = new Book();
        book2.setId(2);
        book2.setTitle("Философия Java");
        book2.setAuthor("Брюс Эккель");

        var book3 = new Book();
        book3.setId(3);
        book3.setTitle("Spring в действии. Шестое издание");
        book3.setAuthor("Уоллс К.");

        books.add(book1);
        books.add(book2);
        books.add(book3);

    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
    }

    @Override
    public void save(Book newBook) throws RuntimeException {
        findById(newBook.getId())
                .ifPresentOrElse(
                        existBook -> {
                            throw new RuntimeException("Книга с id " + existBook.getId() + " уже существует и не будет сохранена!");
                        },
                        () -> books.add(newBook)
                );
    }

    @Override
    public void delete(int id) throws NoSuchElementException {
        findById(id).ifPresentOrElse(
                books::remove,
                () -> {
                    throw new RuntimeException("Книга с id " + id + " не существует");
                }
        );
    }

    @Override
    public void update(Book book) throws NoSuchElementException {
        findById(book.getId())
                .ifPresentOrElse(
                        b -> {
                            delete(book.getId());
                            save(book);
                        },
                        () -> {
                            throw new NoSuchElementException("Книга с id " + book.getId() + " не существует");
                        }
                );
    }
}
