package exam.project.library.controllers;

import exam.project.library.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@WebMvcTest
class AuthorControllerTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllBooks() {

    }

    @Test
    void getBookById() {
    }

    @Test
    void addBook() {
    }

    @Test
    void updateBook() {

    }

    @Test
    void deleteBook() {
    }
}