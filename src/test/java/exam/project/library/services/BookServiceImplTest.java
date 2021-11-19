package exam.project.library.services;

import exam.project.library.models.Book;
import exam.project.library.services.implementations.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class BookServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    BookService bookService;

    Book book1, book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookServiceImpl(jdbcTemplate);

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
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        bookSet.add(book2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(bookSet);

        List<Book> books = bookService.getAllBook();
        assertEquals(2, books.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getBookById() {
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyString())).thenReturn(bookSet);

        List<Book> books = bookService.getBookById(anyLong());
        assertEquals(1, books.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyString());
    }

    @Test
    void saveNewBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString())).thenReturn(1);
        bookService.saveNewBook(book1);
        assertEquals(1, bookService.saveNewBook(book1));
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString());
    }

    @Test
    void updateBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString())).thenReturn(1);
        bookService.updateBook(anyLong(), book1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    void deleteBook() {
        when(jdbcTemplate.update(anyString(), anyString())).thenReturn(1);
        bookService.deleteBook(anyLong());
        verify(jdbcTemplate, times(1)).update(anyString(), anyString());
    }
}