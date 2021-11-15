package exam.project.library.services;

import exam.project.library.models.MemberDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public MemberDto getMemberById(UUID memberId) {
        return null;
    }

    @Override
    public MemberDto saveNewMember(MemberDto memberDto) {
        return null;
    }

    @Override
    public void updateMember(UUID memberId, MemberDto memberDto) {

    }

    @Override
    public void deleteMember(UUID memberId) {

    }
}
