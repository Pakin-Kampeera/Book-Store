package exam.project.library.mappers;

import exam.project.library.models.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherMapper implements RowMapper<Publisher> {
    @Override
    public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setId(rs.getLong("id"));
        publisher.setName(rs.getString("name"));
        publisher.setAddress(rs.getString("address"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setZip(rs.getString("zip"));

        return publisher;
    }
}
