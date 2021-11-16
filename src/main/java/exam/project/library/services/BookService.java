package exam.project.library.services;

import exam.project.library.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book getBookById(Long bookId);

    int saveNewBook(Book bookDto);

    int updateBook(Long bookId, Book bookDto);

    void deleteBook(Long bookId);
}
