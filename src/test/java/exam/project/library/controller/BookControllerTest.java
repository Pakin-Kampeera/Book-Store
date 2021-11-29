package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Book;
import exam.project.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        Set<String> authorsId = new HashSet<>();
        authorsId.add("1L");
        authorsId.add("2L");

        Book book = new Book();
        book.setTitle("Terminator");
        book.setPrice(35.00);
        book.setISBN("0-4147-3111-5");
        book.setPublisherId("1L");
        book.setAuthorId(authorsId);

        this.body = objectMapper.writeValueAsString(book);
    }

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/api/v1/book"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getBookById() throws Exception {
        mockMvc.perform(get("/api/v1/book/{bookId}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void addBook() throws Exception {
        mockMvc.perform(post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateBook() throws Exception {
        mockMvc.perform(put("/api/v1/book/{bookId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/api/v1/book/{bookId}", 1))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}