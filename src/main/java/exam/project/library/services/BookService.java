package exam.project.library.services;

import exam.project.library.models.BookDto;

import java.util.UUID;

public interface BookService {
    BookDto getBookById(UUID bookId);

    BookDto saveNewBook(BookDto bookDto);

    void updateBook(UUID bookId, BookDto bookDto);

    void deleteBook(UUID bookId);
}
