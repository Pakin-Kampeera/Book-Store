package exam.project.library.service.implementations;

import exam.project.library.model.Book;
import exam.project.library.repository.BookRepository;
import exam.project.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBook() {
        return null;
    }

    @Override
    public List<Book> getBookById(Long bookId) {
        return null;
    }

    @Override
    public int saveNewBook(Book book) {
        return 1;
    }

    @Override
    public void updateBook(Long bookId, Book book) {

    }

    @Override
    public void deleteBook(Long bookId) {

    }
}
