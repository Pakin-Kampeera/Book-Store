package exam.project.library.repository;

import exam.project.library.mapper.AuthorMapper;
import exam.project.library.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Repository
public class AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAllAuthor() {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT")
                .add("Books.*, Authors.*")
                .add("FROM")
                .add("((Authors LEFT JOIN Write ON Authors.author_id = Write.author_id)")
                .add("LEFT JOIN Books ON Books.book_id = Write.book_id)");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new AuthorMapper());
    }

    public List<Author> getAuthorById(Long authorId) {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT")
                .add("Books.*, Authors.*")
                .add("FROM")
                .add("((Authors LEFT JOIN Write ON Authors.author_id = Write.author_id)")
                .add("LEFT JOIN Books ON Books.book_id = Write.book_id)")
                .add("WHERE Authors.author_id = ?");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new AuthorMapper(), authorId);
    }

    public int saveNewAuthor(Author author) {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO")
                .add("Authors (firstname, lastname)")
                .add("VALUES (?, ?)");
        log.info("sql = {}", sql);
        return jdbcTemplate.update(sql.toString()
                , author.getFirstName()
                , author.getLastName());
    }

    public void saveWriteBook(Long authorId, Long bookId) {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO")
                .add("WRITE (author_id, book_id)")
                .add("VALUES (?, ?)");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , authorId
                , bookId);
    }

    public void updateAuthor(Long authorId, Author author) {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE")
                .add("Authors")
                .add("SET firstname = ?, lastname = ?")
                .add("WHERE author_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , author.getFirstName()
                , author.getLastName()
                , authorId);
    }

    public void deleteAuthor(Long authorId) {
        final StringJoiner sql = new StringJoiner(" ");
        sql.add("DELETE FROM")
                .add("Authors")
                .add("WHERE author_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , authorId);
    }
}
