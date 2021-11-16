package exam.project.library.services;

import exam.project.library.models.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMember();

    Member getMemberById(Long memberId);

    Member saveNewMember(Member memberDto);

    void updateMember(Long memberId, Member memberDto);

    void deleteMember(Long memberId);
}
