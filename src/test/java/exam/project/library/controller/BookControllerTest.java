package exam.project.library.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exam.project.library.model.Book;
import exam.project.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;
    private String body;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        Set<String> authorsId = new HashSet<>();
        authorsId.add("1L");
        authorsId.add("2L");

        Book book = new Book()
                .setTitle("Terminator")
                .setPrice(35.00)
                .setISBN("0-4147-3111-5")
                .setPublisherId("1L")
                .setAuthorId(authorsId);

        ObjectMapper objectMapper = new ObjectMapper();
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