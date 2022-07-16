package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Author;
import exam.project.library.service.AuthorService;
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
class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;

    @Mock
    private AuthorService authorService;

    private MockMvc mockMvc;
    private String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();

        Author author = new Author()
                .setFirstName("John")
                .setLastName("Kate");

        ObjectMapper objectMapper = new ObjectMapper();
        this.body = objectMapper.writeValueAsString(author);
    }

    @Test
    void getAllAuthors() throws Exception {
        mockMvc.perform(get("/api/v1/author"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAuthorById() throws Exception {
        mockMvc.perform(get("/api/v1/author/{authorId}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void addAuthor() throws Exception {
        mockMvc.perform(post("/api/v1/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void writeBook() throws Exception {
        String content = "{\"authorId\":\"3\",\"bookId\":\"3\"}";
        mockMvc.perform(post("/api/v1/author/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateAuthor() throws Exception {
        mockMvc.perform(put("/api/v1/author/{authorId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteAuthor() throws Exception {
        mockMvc.perform(delete("/api/v1/author/{authorId}", 1))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}