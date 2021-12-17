package exam.project.library.mapper;

import exam.project.library.model.Book;
import exam.project.library.model.Publisher;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PublisherMapper implements ResultSetExtractor<List<Publisher>> {

    @Override
    public List<Publisher> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Publisher> publisherMap = new HashMap<>();
        while (rs.next()) {
            Long publisherId = rs.getLong("publisher_id");
            if (publisherMap.get(publisherId) == null) {
                Publisher publisher = new Publisher()
                        .setPublisherId(rs.getLong("publisher_id"))
                        .setName(rs.getString("name"))
                        .setStreet(rs.getString("street"))
                        .setCity(rs.getString("city"))
                        .setZip(rs.getString("zip"))
                        .setBooks(new HashSet<>());
                publisherMap.put(publisherId, publisher);
            }
            if (rs.getLong("book_id") != 0) {
                Publisher publisher = publisherMap.get(publisherId);
                Set<Book> books = publisher.getBooks();
                Book book = new Book()
                        .setBookId(rs.getLong("book_id"))
                        .setTitle(rs.getString("title"))
                        .setPrice(rs.getDouble("price"))
                        .setISBN(rs.getString("isbn"));
                books.add(book);
                publisher.setBooks(books);
            }
        }
        return new ArrayList<>(publisherMap.values());
    }
}
