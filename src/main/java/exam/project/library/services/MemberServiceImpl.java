package exam.project.library.services;

import exam.project.library.models.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {

    private final JdbcTemplate jdbcTemplate;

    public MemberServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Member getMemberById(UUID memberId) {
        return null;
    }

    @Override
    public Member saveNewMember(Member memberDto) {
        return null;
    }

    @Override
    public void updateMember(UUID memberId, Member memberDto) {

    }

    @Override
    public void deleteMember(UUID memberId) {

    }
}
