package exam.project.library.service.implementations;

import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
import exam.project.library.repository.MemberRepository;
import exam.project.library.repository.TransactionRepository;
import exam.project.library.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;

    public MemberServiceImpl(MemberRepository memberRepository, TransactionRepository transactionRepository) {
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
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
        for (String bookId : transaction.getBookId()) {
            transactionRepository.saveNewTransaction(transaction, bookId);
        }
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
