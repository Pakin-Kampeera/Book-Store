package exam.project.library.mapper;

import exam.project.library.model.Book;
import exam.project.library.model.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MemberMapper implements ResultSetExtractor<List<Member>> {

    @Override
    public List<Member> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Member> memberMap = new HashMap<>();
        while (rs.next()) {
            Long memberId = rs.getLong("member_id");
            if (memberMap.get(memberId) == null) {
                Member member = new Member();
                member.setId(rs.getLong("member_id"));
                member.setFirstName(rs.getString("firstname"));
                member.setLastName(rs.getString("lastname"));
                member.setTelephone(rs.getString("telephone"));
                member.setBooks(new HashSet<>());
                memberMap.put(memberId, member);
            }

            Member member = memberMap.get(memberId);
            Set<Book> books = member.getBooks();
            Book book = new Book();
            book.setId(rs.getLong("book_id"));
            book.setTitle(rs.getString("title"));
            book.setPrice(rs.getString("price"));
            books.add(book);
            member.setBooks(books);
        }
        return new ArrayList<>(memberMap.values());
    }
}
