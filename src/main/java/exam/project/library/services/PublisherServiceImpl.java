package exam.project.library.services;

import exam.project.library.mappers.PublisherMapper;
import exam.project.library.models.Publisher;
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
        String sql = "select * from Publishers";
        return jdbcTemplate.query(sql, new PublisherMapper());
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
        String sql = "select * from Publishers where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{publisherId}, new PublisherMapper());
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
        String sql = "update Publishers set name = ?, street = ?, city = ?, zip = ? where id = ?";
        jdbcTemplate.update(sql
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip());
    }

    @Override
    public void deletePublisher(Long publisherId) {
        String sql = "delete from Publishers where id = ?";
        jdbcTemplate.update(sql, publisherId);
    }
}
