package exam.project.library.mapper;

import exam.project.library.model.Author;
import exam.project.library.model.Book;
import exam.project.library.model.Publisher;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookMapper implements ResultSetExtractor<List<Book>> {

    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Book> bookMap = new HashMap<>();
        while (rs.next()) {
            Long bookId = rs.getLong("book_id");
            if (bookMap.get(bookId) == null) {
                Book book = new Book()
                        .setBookId(bookId)
                        .setTitle(rs.getString("title"))
                        .setPrice(rs.getDouble("price"))
                        .setISBN(rs.getString("isbn"));

                Publisher publisher = new Publisher()
                        .setPublisherId(rs.getLong("publisher_id"))
                        .setName(rs.getString("name"))
                        .setStreet(rs.getString("street"))
                        .setCity(rs.getString("city"))
                        .setZip(rs.getString("zip"));

                book.setPublisher(publisher)
                    .setAuthors(new HashSet<>());
                bookMap.put(bookId, book);
            }

            if (rs.getLong("author_id") != 0) {
                Book book = bookMap.get(bookId);
                Set<Author> authors = book.getAuthors();
                Author author = new Author()
                        .setAuthorId(rs.getLong("author_id"))
                        .setFirstName(rs.getString("firstname"))
                        .setLastName(rs.getString("lastname"));
                authors.add(author);
                book.setAuthors(authors);
            }
        }
        return new ArrayList<>(bookMap.values());
    }
}
