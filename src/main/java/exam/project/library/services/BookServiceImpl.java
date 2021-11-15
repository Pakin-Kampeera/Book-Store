package exam.project.library.services;

import exam.project.library.models.BookDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public BookDto getBookById(UUID bookId) {
        //SELECT * FROM books where id=bookId
        return null;
    }

    @Override
    public BookDto saveNewBook(BookDto bookDto) {
        //INSERT INTO books (author, ...) VALUES ("John", ...)
        return null;
    }

    @Override
    public void updateBook(UUID bookId, BookDto bookDto) {
        //UPDATE books SET author...
    }

    @Override
    public void deleteBook(UUID bookId) {
        //DELETE FROM books WHERE id=bookId
    }
}
