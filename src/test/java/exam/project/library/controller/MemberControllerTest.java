package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Member;
import exam.project.library.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;
    private String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        Member member = new Member()
                .setFirstName("Anna")
                .setLastName("Sweet")
                .setTelephone("025478964");

        ObjectMapper objectMapper = new ObjectMapper();
        this.body = objectMapper.writeValueAsString(member);
    }

    @Test
    void getAllMembers() throws Exception {
        mockMvc.perform(get("/api/v1/member"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getMemberById() throws Exception {
        mockMvc.perform(get("/api/v1/member/{memberId}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void addMember() throws Exception {
        mockMvc.perform(post("/api/v1/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void ownBook() throws Exception {
        String content = "{\"memberId\":\"3\",\"bookId\":\"3\",\"quantity\":\"3\"}";
        mockMvc.perform(post("/api/v1/member/buy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateMember() throws Exception {
        mockMvc.perform(put("/api/v1/member/{memberId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/api/v1/member/{memberId}", 1))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}