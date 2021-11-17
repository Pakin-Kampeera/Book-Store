package exam.project.library.services;

import exam.project.library.mappers.AuthorMapper;
import exam.project.library.models.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final JdbcTemplate jdbcTemplate;

    public AuthorServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> getAllAuthor() {
        String sql = "select * from Authors";
        return jdbcTemplate.query(sql, new AuthorMapper());
    }

    @Override
    public Author getAuthorById(Long authorId) {
        String sql = "select * from Authors where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{authorId}, new AuthorMapper());
    }

    @Override
    public int saveNewAuthor(Author author) {
        String sql = "insert into Authors (firstname, lastname) values (?, ?)";
        return jdbcTemplate.update(sql, author.getFirstName(), author.getLastName());
    }

    @Override
    public void updateAuthor(Long authorId, Author author) {
        String sql = "update Authors set firstname = ?, lastname = ? where id = ?";
        jdbcTemplate.update(sql, author.getFirstName(), author.getLastName(), authorId);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        String sql = "delete from Authors where id = ?";
        jdbcTemplate.update(sql, authorId);
    }
}
