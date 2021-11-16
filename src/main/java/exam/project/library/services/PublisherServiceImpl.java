package exam.project.library.services;

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
        return null;
    }

    @Override
    public Publisher getPublisherById(Long publisherId) {
        return null;
    }

    @Override
    public Publisher saveNewPublisher(Long publisherDto) {
        return null;
    }

    @Override
    public void updatePublisher(Long publisherId, Publisher publisherDto) {

    }

    @Override
    public void deletePublisher(Long publisherId) {

    }
}
