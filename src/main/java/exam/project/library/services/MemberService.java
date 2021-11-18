package exam.project.library.services;

import exam.project.library.models.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMember();

    List<Member> getMemberById(Long memberId);

    int saveNewMember(Member member);

    void buyBook(Long memberId, Long bookId);

    void updateMember(Long memberId, Member member);

    void deleteMember(Long memberId);
}
