package exam.project.library.service.implementations;

import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
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
        return memberRepository.getAllMember();
    }

    @Override
    public List<Member> getMemberById(Long memberId) {
        return memberRepository.getMemberById(memberId);
    }

    @Override
    public int saveNewMember(Member member) {
        return memberRepository.saveNewMember(member);
    }

    @Override
    public void buyBook(Transaction transaction) {
        memberRepository.buyBook(transaction.getMemberId(), transaction.getBookId());

    }

    @Override
    public void updateMember(Long memberId, Member member) {
        memberRepository.updateMember(memberId, member);
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }
}
