package exam.project.library.mappers;

import exam.project.library.models.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = Member.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .address(rs.getString("address"))
                .build();
        return member;
    }
}
