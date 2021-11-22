package exam.project.library.repository;

import exam.project.library.mapper.AuthorMapper;
import exam.project.library.model.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {
    private final JdbcTemplate jdbcTemplate;

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Author> getAllAuthor() {
        String sql = "select Books.*, Authors.* from Authors, Books, Write where Write.author_id = Authors.author_id and Write.book_id = Books.book_id";
        return jdbcTemplate.query(sql
                , new AuthorMapper());
    }

    List<Author> getAuthorById(Long authorId) {
        String sql = "select Books.*, Authors.* from Authors, Books, Write where Write.book_id = Books.book_id and Write.author_id = Authors.author_id and Write.author_id = ?";
        return jdbcTemplate.query(sql
                , new AuthorMapper(), authorId);
    }

    int saveNewAuthor(Author author) {
        String sql = "insert into Authors (firstname, lastname) values (?, ?)";
        return jdbcTemplate.update(sql
                , author.getFirstName()
                , author.getLastName());
    }

    void saveWriteBook(Long authorId, Long bookId) {
        String sql = "insert into Write (author_id, book_id) values (?, ?)";
        jdbcTemplate.update(sql
                , authorId
                , bookId);
    }

    void updateAuthor(Long authorId, Author author) {
        String sql = "update Authors set firstname = ?, lastname = ? where author_id = ?";
        jdbcTemplate.update(sql
                , author.getFirstName()
                , author.getLastName()
                , authorId);
    }

    void deleteAuthor(Long authorId) {
        String sql = "delete from Authors where author_id = ?";
        jdbcTemplate.update(sql
                , authorId);
    }
}
