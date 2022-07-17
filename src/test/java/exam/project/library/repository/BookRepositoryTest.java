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
import static org.mockito.BDDMockito.given;
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
        this.book1 = new Book()
                .setBookId(1L)
                .setTitle("The matrix")
                .setPrice(25.50);

        this.book2 = new Book()
                .setBookId(2L)
                .setTitle("Apocalypse")
                .setPrice(30.00);
    }

    @Test
    void getAllBook() {
        // given
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        bookSet.add(book2);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).willReturn(bookSet);

        // when
        List<Book> books = bookRepository.getAllBook();

        // then
        assertEquals(2, books.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getBookById() {
        // given
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).willReturn(bookSet);

        // when
        List<Book> books = bookRepository.getBookById(1L);

        // then
        assertEquals(1, books.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewBook() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyDouble(), anyLong())).willReturn(1);

        // when
        bookRepository.updateBook(1L, book1);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyDouble(), anyLong());
    }

    @Test
    void updateBook() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyDouble(), anyLong())).willReturn(1);

        // when
        bookRepository.updateBook(1L, book1);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyDouble(), anyLong());
    }

    @Test
    void deleteBook() {
        // given
        given(jdbcTemplate.update(anyString(), anyLong())).willReturn(1);

        // when
        bookRepository.deleteBook(1L);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }
}