package exam.project.library.services;

import exam.project.library.models.Member;

import java.util.UUID;

public interface MemberService {
    Member getMemberById(UUID memberId);

    Member saveNewMember(Member memberDto);

    void updateMember(UUID memberId, Member memberDto);

    void deleteMember(UUID memberId);
}
