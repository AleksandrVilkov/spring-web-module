package ru.edu.springweb.converter.toDto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.edu.springweb.model.domain.Book;
import ru.edu.springweb.model.dto.BookDto;

@Component
public class BookToBookDto implements Converter<Book, BookDto> {

    @Override
    public BookDto convert(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setAuthor(book.getAuthor());
        dto.setTitle(book.getTitle());
        return dto;
    }
}