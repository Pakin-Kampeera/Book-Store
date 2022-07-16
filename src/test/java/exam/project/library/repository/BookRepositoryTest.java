package exam.project.library.repository;

import exam.project.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRepositoryTest {

    @InjectMocks
    private BookRepository bookRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private Book book1, book2;

    @BeforeEach
    void setUp() {
        book1 = new Book()
                .setBookId(1L)
                .setTitle("The matrix")
                .setPrice(25.50);

        book2 = new Book()
                .setBookId(2L)
                .setTitle("Apocalypse")
                .setPrice(30.00);
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