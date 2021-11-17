package exam.project.library.services;

import exam.project.library.mappers.MemberMapper;
import exam.project.library.models.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final JdbcTemplate jdbcTemplate;

    public MemberServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Member> getAllMember() {
        String sql = "select * from Members";
        return jdbcTemplate.query(sql, new MemberMapper());
    }

    @Override
    public Member getMemberById(Long memberId) {
        String sql = "select * from Members where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{memberId}, new MemberMapper());
    }

    @Override
    public int saveNewMember(Member member) {
        String sql = "insert into Members (firstname, lastname, telephone, borrowDate, returnDate) values (?, ?, ?, ?, ?)";
        LocalDate currentDate = LocalDate.now();
        return jdbcTemplate.update(sql
                , member.getFirstName()
                , member.getLastName()
                , member.getTelephone()
                , currentDate
                , currentDate.plusDays(5));
    }

    @Override
    public void updateMember(Long memberId, Member member) {
        String sql = "update Members set firstname = ?, lastname = ?, telephone = ? where id = ?";
        jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getTelephone(), memberId);
    }

    @Override
    public void deleteMember(Long memberId) {
        String sql = "delete from Members where id = ?";
        jdbcTemplate.update(sql, memberId);
    }
}
