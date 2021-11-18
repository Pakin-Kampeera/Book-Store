package exam.project.library.controllers;

import exam.project.library.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorService authorService;

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/api/v1/author"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getBookById() throws Exception {
        mockMvc.perform(get("/api/v1/author/{bookId}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void addBook() throws Exception {
        mockMvc.perform(post("/api/v1/author"))
//                .contentType(MediaType.APPLICATION_JSON)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void writeBook() throws Exception {
        mockMvc.perform(post("/api/v1/author/write"))
//                .contentType(MediaType.APPLICATION_JSON)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateBook() throws Exception {
        mockMvc.perform(put("/api/v1/author/{authorId}", 1))
//                .contentType(MediaType.APPLICATION_JSON)
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/api/v1/author/{authorId}", 1))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}