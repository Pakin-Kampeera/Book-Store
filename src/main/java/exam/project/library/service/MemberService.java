package exam.project.library.service;

import exam.project.library.model.Member;
import exam.project.library.model.Transaction;

import java.util.List;

public interface MemberService {
    List<Member> getAllMember();

    List<Member> getMemberById(Long memberId);

    int saveNewMember(Member member);

    void buyBook(Transaction transaction);

    void updateMember(Long memberId, Member member);

    void deleteMember(Long memberId);
}
