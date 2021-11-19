package exam.project.library.services.implementations;

import exam.project.library.mappers.PublisherMapper;
import exam.project.library.models.Publisher;
import exam.project.library.services.PublisherService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final JdbcTemplate jdbcTemplate;

    public PublisherServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Publisher> getAllPublisher() {
        String sql = "select Publishers.*, Books.* from publishers, books where publishers.publisher_id = books.publisher_id";
        return jdbcTemplate.query(sql, new PublisherMapper());
    }

    @Override
    public List<Publisher> getPublisherById(Long publisherId) {
        String sql = "select Publishers.*, Books.* from publishers, books where publishers.publisher_id = books.publisher_id and Publishers.publisher_id = ?";
        return jdbcTemplate.query(sql, new PublisherMapper(), publisherId);
    }

    @Override
    public int saveNewPublisher(Publisher publisher) {
        String sql = "insert into Publishers (name, street, city, zip) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip());
    }

    @Override
    public void updatePublisher(Long publisherId, Publisher publisher) {
        String sql = "update Publishers set name = ?, street = ?, city = ?, zip = ? where publisher_id = ?";
        jdbcTemplate.update(sql
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip()
                , publisherId);
    }

    @Override
    public void deletePublisher(Long publisherId) {
        String sql = "delete from Books where Books.publisher_id = ?";
        jdbcTemplate.update(sql, publisherId);
    }
}