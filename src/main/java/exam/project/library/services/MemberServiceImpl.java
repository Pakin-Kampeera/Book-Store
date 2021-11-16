package exam.project.library.services;

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
        return null;
    }

    @Override
    public Member getMemberById(Long memberId) {
        return null;
    }

    @Override
    public Member saveNewMember(Member memberDto) {
        return null;
    }

    @Override
    public void updateMember(Long memberId, Member memberDto) {

    }

    @Override
    public void deleteMember(Long memberId) {

    }
}
