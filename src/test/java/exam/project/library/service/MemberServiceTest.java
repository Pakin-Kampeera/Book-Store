package exam.project.library.service;

import exam.project.library.model.Member;
import exam.project.library.repository.MemberRepository;
import exam.project.library.service.implementations.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member member1, member2;

    @BeforeEach
    void setUp() {
        this.member1 = new Member()
                .setMemberId(1L)
                .setFirstName("Steve")
                .setLastName("Jobs")
                .setTelephone("9834757936");

        this.member2 = new Member()
                .setMemberId(2L)
                .setFirstName("Tim")
                .setLastName("Cook")
                .setTelephone("8983498739");
    }

    @Test
    void getAllMember() {
        // given
        List<Member> memberSet = new ArrayList<>();
        memberSet.add(member1);
        memberSet.add(member2);
        given(memberRepository.getAllMember()).willReturn(memberSet);

        // when
        List<Member> members = memberService.getAllMember();

        // then
        assertEquals(2, members.size());
        verify(memberRepository, times(1)).getAllMember();
    }

    @Test
    void getMemberById() {
        // given
        List<Member> memberSet = new ArrayList<>();
        memberSet.add(member1);
        given(memberRepository.getMemberById(anyLong())).willReturn(memberSet);

        // when
        List<Member> members = memberService.getMemberById(anyLong());

        // then
        assertEquals(1, members.size());
        verify(memberRepository, times(1)).getMemberById(anyLong());
    }

    @Test
    void saveNewMember() {
        // given
        given(memberRepository.saveNewMember(any())).willReturn(1);


        // when
        memberService.saveNewMember(any());

        // then
        verify(memberRepository, times(1)).saveNewMember(any());
    }

    @Test
    void updateMember() {
        // when
        memberService.updateMember(anyLong(), any());

        // then
        verify(memberRepository, times(1)).updateMember(anyLong(), any());
    }

    @Test
    void deleteMember() {
        // when
        memberService.deleteMember(anyLong());

        // then
        verify(memberRepository, times(1)).deleteMember(anyLong());
    }
}