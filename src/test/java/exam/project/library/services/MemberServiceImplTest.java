package exam.project.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

class MemberServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    MemberService memberService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllMember() {
//        when(memberService.getAllMember()).thenReturn();
    }

    @Test
    void getMemberById() {
//        when(memberService.getMemberById()).thenReturn();
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