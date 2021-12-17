package exam.project.library.repository;

import exam.project.library.model.Author;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class AuthorRepositoryTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    AuthorRepository authorRepository;

    Author author1, author2;

    Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.authorRepository = new AuthorRepository(jdbcTemplate);

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

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(authorSet);

        List<Author> authors = authorRepository.getAllAuthor();
        assertEquals(2, authors.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getAuthorById() {
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).thenReturn(authorSet);

        List<Author> authors = authorRepository.getAuthorById(1L);
        assertEquals(1, authors.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewAuthor() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString())).thenReturn(1);
        assertEquals(1, authorRepository.saveNewAuthor(author1));
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString());
    }

    @Test
    void saveWriteBook() {
        when(jdbcTemplate.update(anyString(), anyLong(), anyLong())).thenReturn(1);
        authorRepository.saveWriteBook(1L, 1L);
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong(), anyLong());
    }

    @Test
    void updateAuthor() {
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyLong())).thenReturn(1);
        authorRepository.updateAuthor(1L, author1);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deleteAuthor() {
        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);
        authorRepository.deleteAuthor(1L);
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }

}