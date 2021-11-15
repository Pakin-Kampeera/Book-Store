package exam.project.library.services;

import exam.project.library.models.MemberDto;

import java.util.UUID;

public interface MemberService {
    MemberDto getMemberById(UUID memberId);

    MemberDto saveNewMember(MemberDto memberDto);

    void updateMember(UUID memberId, MemberDto memberDto);

    void deleteMember(UUID memberId);
}
