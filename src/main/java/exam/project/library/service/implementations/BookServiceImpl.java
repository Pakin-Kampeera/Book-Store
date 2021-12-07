package exam.project.library.service.implementations;

import exam.project.library.model.Book;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.repository.BookRepository;
import exam.project.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Cacheable(value = "books")
    public List<Book> getAllBook() {
        return bookRepository.getAllBook();
    }

    @Override
    @Cacheable(value = "book", key = "#bookId", unless = "#result==null")
    public List<Book> getBookById(Long bookId) {
        return bookRepository.getBookById(bookId);
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public void saveNewBook(Book book) {
        Long index = bookRepository.saveNewBook(book, Long.parseLong(book.getPublisherId()));
        log.info("index = {}", index);
        for (String elem : book.getAuthorId()) {
            authorRepository.saveWriteBook(Long.parseLong(elem), index);
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "books", allEntries = true),
            @CacheEvict(value = "book", key = "#bookId")
    })
    public void updateBook(Long bookId, Book book) {
        bookRepository.updateBook(bookId, book);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "books", allEntries = true),
            @CacheEvict(value = "book", key = "#bookId")
    })
    public void deleteBook(Long bookId) {
        bookRepository.deleteBook(bookId);
    }
}
