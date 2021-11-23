package exam.project.library.repository;

import exam.project.library.mapper.PublisherMapper;
import exam.project.library.model.Publisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherRepository {
    private final JdbcTemplate jdbcTemplate;

    public PublisherRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Publisher> getAllPublisher() {
        String sql = "select Publishers.*, Books.* from publishers, books where publishers.publisher_id = books.publisher_id";
        return jdbcTemplate.query(sql
                , new PublisherMapper());
    }

    public List<Publisher> getPublisherById(Long publisherId) {
        String sql = "select Publishers.*, Books.* from publishers, books where publishers.publisher_id = books.publisher_id and Publishers.publisher_id = ?";
        return jdbcTemplate.query(sql
                , new PublisherMapper()
                , publisherId);
    }

    public int saveNewPublisher(Publisher publisher) {
        String sql = "insert into Publishers (name, street, city, zip) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip());
    }

    public void updatePublisher(Long publisherId, Publisher publisher) {
        String sql = "update Publishers set name = ?, street = ?, city = ?, zip = ? where publisher_id = ?";
        jdbcTemplate.update(sql
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip()
                , publisherId);
    }

    public void deletePublisher(Long publisherId) {
        String sql = "delete from Books where Books.publisher_id = ?";
        jdbcTemplate.update(sql
                , publisherId);
    }
}
