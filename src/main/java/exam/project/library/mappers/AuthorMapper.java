package exam.project.library.mappers;

import exam.project.library.models.Author;
import exam.project.library.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setId(rs.getLong("authorid"));
        author.setFirstName(rs.getString("firstname"));
        author.setLastName(rs.getString("lastname"));

        System.out.println(rowNum);
        Book book = new Book();
        book.setId(rs.getLong("bookid"));
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getString("price"));

        author.setBook(book);

        return author;
    }
}
