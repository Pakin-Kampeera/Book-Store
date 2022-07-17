package exam.project.library.repository;

import exam.project.library.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberRepositoryTest {

    @InjectMocks
    private MemberRepository memberRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

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
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).willReturn(memberSet);

        // when
        List<Member> members = memberRepository.getAllMember();

        // then
        assertEquals(2, members.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getMemberById() {
        // given
        List<Member> memberSet = new ArrayList<>();
        memberSet.add(member1);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).willReturn(memberSet);

        // when
        List<Member> members = memberRepository.getMemberById(1L);

        // then
        assertEquals(1, members.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewMember() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString())).willReturn(1);

        // when
        memberRepository.saveNewMember(member1);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void updateMember() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyLong())).willReturn(1);

        // when
        memberRepository.updateMember(1L, member1);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deleteMember() {
        // given
        given(jdbcTemplate.update(anyString(), anyLong())).willReturn(1);

        // when
        memberRepository.deleteMember(1L);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}