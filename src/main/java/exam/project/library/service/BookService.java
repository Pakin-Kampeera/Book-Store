package exam.project.library.service;

import exam.project.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    List<Book> getBookById(Long bookId);

    void saveNewBook(Book book);

    void updateBook(Long bookId, Book book);

    void deleteBook(Long bookId);
}
