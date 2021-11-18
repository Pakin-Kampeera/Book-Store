package exam.project.library.mappers;

import exam.project.library.models.Book;
import exam.project.library.models.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setId(rs.getLong("member_id"));
        member.setFirstName(rs.getString("firstname"));
        member.setLastName(rs.getString("lastname"));
        member.setTelephone(rs.getString("telephone"));

        Book book = new Book();
        book.setId(rs.getLong("book_id"));
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getString("price"));

        return member;
    }
}
