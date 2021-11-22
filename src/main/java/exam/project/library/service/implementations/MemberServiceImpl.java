package exam.project.library.service.implementations;

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
        return null;
    }

    @Override
    public List<Member> getMemberById(Long memberId) {
        return null;
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
