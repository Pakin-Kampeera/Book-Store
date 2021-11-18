package exam.project.library.services;

import exam.project.library.mappers.MemberMapper;
import exam.project.library.models.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final JdbcTemplate jdbcTemplate;

    public MemberServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Member> getAllMember() {
        String sql = "select * from Members, Books, Buy where Buy.member_id = Members.member_id and Buy.book_id = Books.book_id";
        return jdbcTemplate.query(sql, new MemberMapper());
    }

    @Override
    public List<Member> getMemberById(Long memberId) {
        String sql = "select * from Members, Books, Buy where Buy.member_id = Members.member_id and Buy.book_id = Books.book_id and Members.member_id = ?";
        return jdbcTemplate.query(sql, new Object[]{memberId}, new MemberMapper());
    }

    @Override
    public int saveNewMember(Member member) {
        String sql = "insert into Members (firstname, lastname, telephone) values (?, ?, ?)";
        return jdbcTemplate.update(sql
                , member.getFirstName()
                , member.getLastName()
                , member.getTelephone());
    }

    @Override
    public void buyBook(Long memberId, Long bookId) {
        String sql = "insert into Buy (member_id, book_id) values (?, ?)";
        jdbcTemplate.update(sql, memberId, bookId);
    }

    @Override
    public void updateMember(Long memberId, Member member) {
        String sql = "update Members set firstname = ?, lastname = ?, telephone = ? where member_id = ?";
        jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getTelephone(), memberId);
    }

    @Override
    public void deleteMember(Long memberId) {
        String sql = "delete from Members where member_id = ?";
        jdbcTemplate.update(sql, memberId);
    }
}
