package exam.project.library.services;

import exam.project.library.mappers.BookMapper;
import exam.project.library.models.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final JdbcTemplate jdbcTemplate;

    public BookServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllBook() {
        String sql = "select * from Books, Authors, Publishers, Write where Books.publisher_id = publishers.publisher_id and Write.author_id = Authors.author_id and Write.book_id = Books.book_id";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public List<Book> getBookById(Long bookId) {
        String sql = "select * from Books, Authors, Publishers, Write where Books.publisher_id = publishers.publisher_id and Write.author_id = Authors.author_id and Write.book_id = Books.book_id and Books.book_id = ?";
        return jdbcTemplate.query(sql,
                new Object[]{bookId},
                new BookMapper());
    }

    @Override
    public int saveNewBook(Book book) {
        String sql = "insert into Books (title, price) values (?, ?)";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getPrice());
    }

    @Override
    public void updateBook(Long bookId, Book book) {
        String sql = "update Books set title = ?, price = ? where book_id = ?";
        jdbcTemplate.update(sql
                , book.getTitle()
                , book.getPrice()
                , bookId);
    }

    @Override
    public void deleteBook(Long bookId) {
        String sql = "delete from Books where book_id = ?";
        jdbcTemplate.update(sql, bookId);
    }
}
