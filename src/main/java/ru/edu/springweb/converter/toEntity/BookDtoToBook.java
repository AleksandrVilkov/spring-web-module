package ru.edu.springweb.converter.toEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.edu.springweb.model.domain.Book;
import ru.edu.springweb.model.dto.BookDto;

@Component
public class BookDtoToBook implements Converter<BookDto, Book> {

    @Override
    public Book convert(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        return book;
    }
}