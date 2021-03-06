package exam.project.library.repository;

import exam.project.library.mapper.BookMapper;
import exam.project.library.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.StringJoiner;

@Log4j2
@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BLANK_SPACE = " ";

    public List<Book> getAllBook() {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Books.*, Authors.*, Publishers.*")
                .add("FROM")
                .add("Books, Authors, Publishers, Write")
                .add("Where Books.publisher_id = Publishers.publisher_id")
                .add("AND Write.author_id = Authors.author_id")
                .add("AND Write.book_id = Books.book_id");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new BookMapper());
    }

    public List<Book> getBookById(Long bookId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Books.*, Authors.*, Publishers.*")
                .add("FROM")
                .add("Books, Authors, Publishers, Write")
                .add("Where Books.publisher_id = Publishers.publisher_id")
                .add("AND Write.author_id = Authors.author_id")
                .add("AND Write.book_id = Books.book_id")
                .add("AND Books.book_id = ?");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new BookMapper()
                , bookId);
    }

    public long saveNewBook(Book book, Long publisherId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        sql.add("INSERT INTO")
                .add("Books (title, price, publisher_id, isbn)")
                .add("VALUES (?, ?, ?, ?)");

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql.toString(), new String[]{"book_id"});
            ps.setString(1, book.getTitle());
            ps.setDouble(2, book.getPrice());
            ps.setLong(3, publisherId);
            ps.setString(4, book.getISBN());
            return ps;
        }, keyHolder);

        return keyHolder.getKey()
                .longValue();
    }

    public void updateBook(Long bookId, Book book) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("UPDATE")
                .add("Books")
                .add("SET title = ?, price = ?")
                .add("WHERE book_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , book.getTitle()
                , book.getPrice()
                , bookId);
    }

    public void deleteBook(Long bookId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("DELETE FROM")
                .add("Books")
                .add("WHERE book_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , bookId);
    }
}