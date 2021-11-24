package exam.project.library.mapper;

import exam.project.library.model.Author;
import exam.project.library.model.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuthorMapper implements ResultSetExtractor<List<Author>> {
    @Override
    public List<Author> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Author> authorMap = new HashMap<>();
        while (rs.next()) {
            Long authorId = rs.getLong("author_id");
            if (authorMap.get(authorId) == null) {
                Author author = new Author();
                author.setId(authorId);
                author.setFirstName(rs.getString("firstname"));
                author.setLastName(rs.getString("lastname"));
                author.setBooks(new HashSet<>());
                authorMap.put(authorId, author);
            }

            Author author = authorMap.get(authorId);
            Set<Book> books = author.getBooks();
            Book book = new Book();
            if (rs.getLong("book_id") != 0) {
                book.setId(rs.getLong("book_id"));
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getString("price"));
                books.add(book);
            }
            author.setBooks(books);
        }
        return new ArrayList<>(authorMap.values());
    }
}