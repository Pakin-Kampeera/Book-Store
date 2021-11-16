package exam.project.library.services;

import exam.project.library.models.Publisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final JdbcTemplate jdbcTemplate;

    public PublisherServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Publisher getPublisherById(UUID publisherId) {
        return null;
    }

    @Override
    public Publisher saveNewPublisher(Publisher publisherDto) {
        return null;
    }

    @Override
    public void updatePublisher(UUID publisherId, Publisher publisherDto) {

    }

    @Override
    public void deletePublisher(UUID publisherId) {

    }
}
