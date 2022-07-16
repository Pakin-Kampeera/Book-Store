package exam.project.library.repository;

import exam.project.library.model.Author;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryTest {

    @InjectMocks
    private AuthorRepository authorRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private Author author1, author2;

    private Book book;

    @BeforeEach
    void setUp() {
        Set<Book> bookSet = new HashSet<>();
        book = new Book()
                .setBookId(1L)
                .setTitle("Tester")
                .setPrice(50.00);
        bookSet.add(book);

        author1 = new Author()
                .setAuthorId(1L)
                .setFirstName("Peter")
                .setLastName("Kim");

        author2 = new Author()
                .setAuthorId(2L)
                .setFirstName("Bob")
                .setLastName("Euro");
    }

    @Test
    void getAllAuthor() {
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);
        authorSet.add(author2);

        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).willReturn(authorSet);

        List<Author> authors = authorRepository.getAllAuthor();
        assertEquals(2, authors.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getAuthorById() {
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);

        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).willReturn(authorSet);

        List<Author> authors = authorRepository.getAuthorById(1L);
        assertEquals(1, authors.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewAuthor() {
        given(jdbcTemplate.update(anyString(), anyString(), anyString())).willReturn(1);

        assertEquals(1, authorRepository.saveNewAuthor(author1));

        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString());
    }

    @Test
    void saveWriteBook() {
        given(jdbcTemplate.update(anyString(), anyLong(), anyLong())).willReturn(1);

        authorRepository.saveWriteBook(1L, 1L);

        verify(jdbcTemplate, times(1)).update(anyString(), anyLong(), anyLong());
    }

    @Test
    void updateAuthor() {
        given(jdbcTemplate.update(anyString(), anyString(), anyString(), anyLong())).willReturn(1);

        authorRepository.updateAuthor(1L, author1);

        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deleteAuthor() {
        given(jdbcTemplate.update(anyString(), anyLong())).willReturn(1);

        authorRepository.deleteAuthor(1L);

        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }

}