package exam.project.library.repository;

import exam.project.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class BookRepositoryTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    BookRepository bookRepository;

    Book book1, book2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.bookRepository = new BookRepository(jdbcTemplate);

        book1 = new Book();
        book1.setBookId(1L);
        book1.setTitle("The matrix");
        book1.setPrice(25.50);

        book2 = new Book();
        book2.setBookId(2L);
        book2.setTitle("Apocalypse");
        book2.setPrice(30.00);
    }

    @Test
    void getAllBook() {
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        bookSet.add(book2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(bookSet);

        List<Book> books = bookRepository.getAllBook();
        assertEquals(2, books.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getBookById() {
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(bookSet);

        List<Book> books = bookRepository.getBookById(1L);
        assertEquals(1, books.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyDouble(), anyLong())).thenReturn(1);
        bookRepository.updateBook(1L, book1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyDouble(), anyLong());
    }

    @Test
    void updateBook() {
        when(jdbcTemplate.update(anyString(), anyString(), anyDouble(), anyLong())).thenReturn(1);
        bookRepository.updateBook(1L, book1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyDouble(), anyLong());
    }

    @Test
    void deleteBook() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);
        bookRepository.deleteBook(1L);
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}