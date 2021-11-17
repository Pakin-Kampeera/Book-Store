package exam.project.library.mappers;

import exam.project.library.models.Author;
import exam.project.library.models.Book;
import exam.project.library.models.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getString("price"));

        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("firstName"));
        author.setLastName(rs.getString("lastName"));

//        book.setAuthor(author);

        Publisher publisher = new Publisher();
        publisher.setId(rs.getLong("publisherid"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setZip(rs.getString("zip"));

//        book.setPublisher(publisher);

        return book;
    }
}
