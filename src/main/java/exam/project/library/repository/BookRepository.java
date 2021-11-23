package exam.project.library.repository;

import exam.project.library.mapper.BookMapper;
import exam.project.library.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBook() {
        String sql = "select * from Books, Authors, Publishers, Write where Books.publisher_id = publishers.publisher_id and Write.author_id = Authors.author_id and Write.book_id = Books.book_id";
        return jdbcTemplate.query(sql
                , new BookMapper());
    }

    public List<Book> getBookById(Long bookId) {
        String sql = "select * from Books, Authors, Publishers, Write where Books.publisher_id = publishers.publisher_id and Write.author_id = Authors.author_id and Write.book_id = Books.book_id and Books.book_id = ?";
        return jdbcTemplate.query(sql
                , new BookMapper()
                , bookId);
    }

    public int saveNewBook(Book book) {
        String sql = "insert into Books (title, price) values (?, ?)";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getPrice());
    }

    public void updateBook(Long bookId, Book book) {
        String sql = "update Books set title = ?, price = ? where book_id = ?";
        jdbcTemplate.update(sql
                , book.getTitle()
                , book.getPrice()
                , bookId);
    }

    public void deleteBook(Long bookId) {
        String sql = "delete from Books where book_id = ?";
        jdbcTemplate.update(sql
                , bookId);
    }
}
