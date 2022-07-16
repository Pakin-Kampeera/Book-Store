package exam.project.library.repository;

import exam.project.library.mapper.PublisherMapper;
import exam.project.library.model.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Log4j2
@Repository
@RequiredArgsConstructor
public class PublisherRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String BLANK_SPACE = " ";

    public List<Publisher> getAllPublisher() {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Publishers.*, Books.*")
                .add("FROM")
                .add("Publishers")
                .add("LEFT JOIN Books ON Publishers.publisher_id = Books.publisher_id");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new PublisherMapper());
    }

    public List<Publisher> getPublisherById(Long publisherId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("SELECT")
                .add("Publishers.*, Books.*")
                .add("FROM")
                .add("Publishers")
                .add("LEFT JOIN Books ON Publishers.publisher_id = Books.publisher_id")
                .add("WHERE Publishers.publisher_id = ?");
        log.info("sql = {}", sql);
        return jdbcTemplate.query(sql.toString()
                , new PublisherMapper()
                , publisherId);
    }

    public int saveNewPublisher(Publisher publisher) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("INSERT INTO")
                .add("Publishers (name, street, city, zip)")
                .add("VALUES (?, ?, ?, ?)");
        log.info("sql = {}", sql);
        return jdbcTemplate.update(sql.toString()
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip());
    }

    public void updatePublisher(Long publisherId, Publisher publisher) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("UPDATE")
                .add("Publishers")
                .add("SET name = ?, street = ?, city = ?, zip = ?")
                .add("WHERE publisher_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , publisher.getName()
                , publisher.getStreet()
                , publisher.getCity()
                , publisher.getZip()
                , publisherId);
    }

    public void deletePublisher(Long publisherId) {
        final StringJoiner sql = new StringJoiner(BLANK_SPACE);
        sql.add("DELETE FROM")
                .add("Books")
                .add("WHERE Books.publisher_id = ?");
        log.info("sql = {}", sql);
        jdbcTemplate.update(sql.toString()
                , publisherId);
    }
}