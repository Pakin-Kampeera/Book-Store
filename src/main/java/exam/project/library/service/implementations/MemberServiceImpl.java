package exam.project.library.service.implementations;

import exam.project.library.mapper.MemberMapper;
import exam.project.library.model.Member;
import exam.project.library.repository.MemberRepository;
import exam.project.library.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMember() {
        String sql = "select * from Members, Books, Buy where Buy.member_id = Members.member_id and Buy.book_id = Books.book_id";
        return jdbcTemplate.query(sql, new MemberMapper());
    }

    @Override
    public List<Member> getMemberById(Long memberId) {
        String sql = "select * from Members, Books, Buy where Buy.member_id = Members.member_id and Buy.book_id = Books.book_id and Members.member_id = ?";
        return jdbcTemplate.query(sql, new MemberMapper(), memberId);
    }

    @Override
    public int saveNewMember(Member member) {
        return 1;
    }

    @Override
    public void buyBook(Long memberId, Long bookId) {

    }

    @Override
    public void updateMember(Long memberId, Member member) {

    }

    @Override
    public void deleteMember(Long memberId) {

    }
}
