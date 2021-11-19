package exam.project.library.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

class BookServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllBook() {
//        when(bookService.getAllBook()).thenReturn();
    }

    @Test
    void getBookById() {
//        when(bookService.getBookById()).thenReturn();
    }

    @Test
    void saveNewBook() {
//        when(bookService.saveNewBook()).thenReturn();
    }

    @Test
    void updateBook() {
//        when(bookService.updateBook()).thenReturn();
    }

    @Test
    void deleteBook() {
//        when(bookService.deleteBook()).thenReturn();
    }
}