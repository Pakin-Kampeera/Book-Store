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
                Member member = new Member()
                        .setMemberId(rs.getLong("member_id"))
                        .setFirstName(rs.getString("firstname"))
                        .setLastName(rs.getString("lastname"))
                        .setTelephone(rs.getString("telephone"))
                        .setBooks(new HashSet<>());
                memberMap.put(memberId, member);
            }

            if (rs.getLong("book_id") != 0) {
                Member member = memberMap.get(memberId);
                Set<Book> books = member.getBooks();
                Book book = new Book()
                        .setBookId(rs.getLong("book_id"))
                        .setTitle(rs.getString("title"))
                        .setPrice(rs.getDouble("price"))
                        .setISBN(rs.getString("isbn"));
                books.add(book);
                member.setBooks(books);
            }
        }
        return new ArrayList<>(memberMap.values());
    }
}
