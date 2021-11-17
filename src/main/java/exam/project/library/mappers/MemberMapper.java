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
        member.setId(rs.getLong("memberid"));
        member.setFirstName(rs.getString("firstName"));
        member.setLastName(rs.getString("lastName"));
        member.setTelephone(rs.getString("telephone"));
        member.setBorrowDate(rs.getTimestamp("borrowdate").toLocalDateTime().toLocalDate());
        member.setReturnDate(rs.getTimestamp("returndate").toLocalDateTime().toLocalDate());

        Book book = new Book();
        book.setId(rs.getLong("bookid"));
        book.setTitle(rs.getString("title"));
        book.setPrice(rs.getString("price"));

//        member.setBook(book);

        return member;
    }
}
