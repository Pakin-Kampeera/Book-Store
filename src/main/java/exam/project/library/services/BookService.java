package exam.project.library.services;

import exam.project.library.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book getBookById(Long bookId);

    int saveNewBook(Book book);

    int updateBook(Long bookId, Book book);

    void deleteBook(Long bookId);
}
