package exam.project.library.services;

import exam.project.library.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class MemberServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    MemberService memberService;

    Member member1, member2;

    @BeforeEach
    void setUp() {
        member1 = new Member();
        member1.setId(1L);
        member1.setFirstName("Steve");
        member1.setLastName("Jobs");
        member1.setTelephone("9834757936");

        member2 = new Member();
        member2.setId(2L);
        member2.setFirstName("Tim");
        member2.setLastName("Cook");
        member2.setTelephone("8983498739");
    }

    @Test
    void getAllMember() {
        Set<Member> memberSet = new HashSet<>();
        memberSet.add(member1);
        memberSet.add(member2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(memberSet);

        List<Member> members = memberService.getAllMember();
        assertEquals(2, members.size());
    }

    @Test
    void getMemberById() {
        Set<Member> memberSet = new HashSet<>();
        memberSet.add(member1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyString())).thenReturn(memberSet);

        List<Member> members = memberService.getMemberById(anyLong());
        assertEquals(1, members.size());
    }

    @Test
    void saveNewMember() {
//        when(memberService.saveNewMember()).thenReturn();
    }

    @Test
    void buyBook() {
//        when(memberService.buyBook()).thenReturn();
    }

    @Test
    void updateMember() {
//        when(memberService.updateMember()).thenReturn();
    }

    @Test
    void deleteMember() {
//        when(memberService.deleteMember()).thenReturn();
    }
}