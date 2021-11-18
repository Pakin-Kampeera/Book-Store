package exam.project.library.mappers;

import exam.project.library.models.Book;
import exam.project.library.models.Publisher;
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
                Publisher publisher = new Publisher();
                publisher.setId(rs.getLong("publisher_id"));
                publisher.setName(rs.getString("name"));
                publisher.setStreet(rs.getString("street"));
                publisher.setCity(rs.getString("city"));
                publisher.setZip(rs.getString("zip"));
                publisher.setBooks(new HashSet<>());
                publisherMap.put(publisherId, publisher);
            }

            Publisher publisher = publisherMap.get(publisherId);
            Set<Book> books = publisher.getBooks();
            Book book = new Book();
            book.setId(rs.getLong("book_id"));
            book.setTitle(rs.getString("title"));
            book.setPrice(rs.getString("price"));
            books.add(book);
            publisher.setBooks(books);

        }
        return new ArrayList<>(publisherMap.values());
    }
}
