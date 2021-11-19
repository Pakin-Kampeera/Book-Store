package exam.project.library.services;

import exam.project.library.models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    BookService bookService;

    Book book1, book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book1 = new Book();
        book1.setId(1L);
        book1.setTitle("The matrix");
        book1.setPrice("25.50");

        book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Apocalypse");
        book2.setPrice("30.00");
    }

    @Test
    void getAllBook() {
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);
        bookSet.add(book2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(bookSet);

        List<Book> books = bookService.getAllBook();
        assertEquals(2, books.size());
    }

    @Test
    void getBookById() {
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyString())).thenReturn(bookSet);

        List<Book> books = bookService.getBookById(anyLong());
        assertEquals(1, books.size());
    }

    @Test
    void saveNewBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString())).thenReturn(1);
        assertEquals(1, bookService.saveNewBook(book1));
    }

    @Test
    void updateBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void deleteBook() {
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString());
    }
}