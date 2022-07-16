package exam.project.library.service.implementations;

import exam.project.library.model.Member;
import exam.project.library.model.Transaction;
import exam.project.library.repository.MemberRepository;
import exam.project.library.repository.TransactionRepository;
import exam.project.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Cacheable(value = "members")
    public List<Member> getAllMember() {
        return memberRepository.getAllMember();
    }

    @Override
    @Cacheable(value = "member", key = "#memberId", unless = "#result==null")
    public List<Member> getMemberById(Long memberId) {
        return memberRepository.getMemberById(memberId);
    }

    @Override
    @CacheEvict(value = "members", allEntries = true)
    public void saveNewMember(Member member) {
        memberRepository.saveNewMember(member);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "transactions", allEntries = true),
            @CacheEvict(value = "transaction", allEntries = true)
    })
    public void buyBook(Transaction transaction) {
        transactionRepository.saveNewTransaction(transaction);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "members", allEntries = true),
            @CacheEvict(value = "member", key = "#memberId")
    })
    public void updateMember(Long memberId, Member member) {
        memberRepository.updateMember(memberId, member);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "members", allEntries = true),
            @CacheEvict(value = "member", key = "#memberId")
    })
    public void deleteMember(Long memberId) {
        memberRepository.deleteMember(memberId);
    }
}
