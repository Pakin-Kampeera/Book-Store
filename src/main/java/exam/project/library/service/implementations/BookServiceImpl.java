package exam.project.library.service.implementations;

import exam.project.library.model.Book;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.repository.BookRepository;
import exam.project.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.getAllBook();
    }

    @Override
    public List<Book> getBookById(Long bookId) {
        return bookRepository.getBookById(bookId);
    }

    @Override
    public void saveNewBook(Book book) {
        Long index = bookRepository.saveNewBook(book, Long.parseLong(book.getPublisherId()));
        log.info("index = {}", index);
        for (String elem : book.getAuthorId()) {
            authorRepository.saveWriteBook(Long.parseLong(elem), index);
        }
    }

    @Override
    public void updateBook(Long bookId, Book book) {
        bookRepository.updateBook(bookId, book);
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteBook(bookId);
    }
}
