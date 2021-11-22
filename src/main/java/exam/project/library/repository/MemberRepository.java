package exam.project.library.repository;

import exam.project.library.mapper.MemberMapper;
import exam.project.library.model.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Member> getAllMember() {
        String sql = "select * from Members, Books, Buy where Buy.member_id = Members.member_id and Buy.book_id = Books.book_id";
        return jdbcTemplate.query(sql
                , new MemberMapper());
    }

    List<Member> getMemberById(Long memberId) {
        String sql = "select * from Members, Books, Buy where Buy.member_id = Members.member_id and Buy.book_id = Books.book_id and Members.member_id = ?";
        return jdbcTemplate.query(sql
                , new MemberMapper()
                , memberId);
    }

    int saveNewMember(Member member) {
        String sql = "insert into Members (firstname, lastname, telephone) values (?, ?, ?)";
        return jdbcTemplate.update(sql
                , member.getFirstName()
                , member.getLastName()
                , member.getTelephone());
    }

    void buyBook(Long memberId, Long bookId) {
        String sql = "insert into Buy (member_id, book_id) values (?, ?)";
        jdbcTemplate.update(sql
                , memberId
                , bookId);
    }

    void updateMember(Long memberId, Member member) {
        String sql = "update Members set firstname = ?, lastname = ?, telephone = ? where member_id = ?";
        jdbcTemplate.update(sql
                , member.getFirstName()
                , member.getLastName()
                , member.getTelephone()
                , memberId);
    }

    void deleteMember(Long memberId) {
        String sql = "delete from Members where member_id = ?";
        jdbcTemplate.update(sql
                , memberId);
    }
}
