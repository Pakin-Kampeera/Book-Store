package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Author;
import exam.project.library.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthorService authorService;

    String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Kate");
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