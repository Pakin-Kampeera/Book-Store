package exam.project.library.mappers;

import exam.project.library.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = Book.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .author(rs.getString("author"))
                .price(rs.getString("price"))
                .available(rs.getBoolean("available"))
                .build();
        return book;
    }
}
