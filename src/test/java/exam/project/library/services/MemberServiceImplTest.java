package exam.project.library.services;

import exam.project.library.models.Member;
import exam.project.library.services.implementations.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class MemberServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    MemberService memberService;

    Member member1, member2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        memberService = new MemberServiceImpl(jdbcTemplate);

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
        List<Member> memberSet = new ArrayList<>();
        memberSet.add(member1);
        memberSet.add(member2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(memberSet);

        List<Member> members = memberService.getAllMember();
        assertEquals(2, members.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getMemberById() {
        Set<Member> memberSet = new HashSet<>();
        memberSet.add(member1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyString())).thenReturn(memberSet);

        List<Member> members = memberService.getMemberById(anyLong());
        assertEquals(1, members.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyString());
    }

    @Test
    void saveNewMember() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        memberService.saveNewMember(member1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void buyBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString())).thenReturn(1);
        memberService.buyBook(anyLong(), anyLong());
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString());
    }

    @Test
    void updateMember() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        memberService.updateMember(anyLong(), member1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void deleteMember() {
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);
        memberService.deleteMember(anyLong());
        verify(jdbcTemplate, times(1)).update(anyString(), anyString());
    }
}