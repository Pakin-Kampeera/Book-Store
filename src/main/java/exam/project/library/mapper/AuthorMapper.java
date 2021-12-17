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
                Author author = new Author()
                        .setAuthorId(authorId)
                        .setFirstName(rs.getString("firstname"))
                        .setLastName(rs.getString("lastname"))
                        .setBooks(new HashSet<>());
                authorMap.put(authorId, author);
            }

            if (rs.getLong("book_id") != 0) {
                Author author = authorMap.get(authorId);
                Set<Book> books = author.getBooks();
                Book book = new Book()
                        .setBookId(rs.getLong("book_id"))
                        .setTitle(rs.getString("title"))
                        .setPrice(rs.getDouble("price"))
                        .setISBN(rs.getString("isbn"));
                books.add(book);
                author.setBooks(books);
            }
        }
        return new ArrayList<>(authorMap.values());
    }
}