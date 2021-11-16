package exam.project.library.mappers;

import exam.project.library.models.Publisher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PublisherMapper implements RowMapper<Publisher> {

    @Override
    public Publisher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Publisher publisher = Publisher.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .address(rs.getString("address"))
                .telephone(rs.getString("telephone"))
                .email(rs.getString("email"))
                .build();
        return publisher;
    }
}
