package ru.edu.springweb.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.edu.springweb.model.domain.Book;
import ru.edu.springweb.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Книга не найдена"));
    }

    @Override
    public void addBook(Book book) {
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Ошибка добавления: " + e.getMessage());
        }
    }

    @Override
    public void updateBook(int id, Book book) {
        try {
            bookRepository.update(book);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Ошибка обновления: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(int id) {
        try {
            bookRepository.delete(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Ошибка удаления: " + e.getMessage());
        }
    }
}
