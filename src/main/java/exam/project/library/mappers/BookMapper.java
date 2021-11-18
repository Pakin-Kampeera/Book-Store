package exam.project.library.mappers;

import exam.project.library.models.Author;
import exam.project.library.models.Book;
import exam.project.library.models.Publisher;
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
                Book book = new Book();
                book.setId(bookId);
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getString("price"));

                Publisher publisher = new Publisher();
                publisher.setId(rs.getLong("publisher_id"));
                publisher.setName(rs.getString("name"));
                publisher.setStreet(rs.getString("street"));
                publisher.setCity(rs.getString("city"));
                publisher.setZip(rs.getString("zip"));

                book.setPublisher(publisher);
                book.setAuthors(new HashSet<>());
                bookMap.put(bookId, book);
            }

            Book book = bookMap.get(bookId);
            Set<Author> authors = book.getAuthors();
            Author author = new Author();
            author.setId(rs.getLong("author_id"));
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname"));
            authors.add(author);
            book.setAuthors(authors);
        }
        return new ArrayList<>(bookMap.values());
    }
}
