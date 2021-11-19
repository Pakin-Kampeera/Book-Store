package exam.project.library.services;

import exam.project.library.models.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    AuthorService authorService;

    Author author1, author2;

    @BeforeEach
    void setUp() {
        author1 = new Author();
        author1.setId(1L);
        author1.setFirstName("Peter");
        author1.setLastName("Kim");

        author2 = new Author();
        author2.setId(2L);
        author2.setFirstName("Bob");
        author2.setLastName("Euro");
    }

    @Test
    void getAllAuthor() {
        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author1);
        authorSet.add(author2);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).thenReturn(authorSet);

        List<Author> authors = authorService.getAllAuthor();
        assertEquals(2, authors.size());
    }

    @Test
    void getAuthorById() {
        Set<Author> authorSet = new HashSet<>();
        authorSet.add(author1);

        when(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyString())).thenReturn(authorSet);

        List<Author> authors = authorService.getAuthorById(anyLong());
        assertEquals(1, authors.size());
    }

    @Test
    void saveNewAuthor() {
//        when(authorService.saveNewAuthor()).thenReturn();
    }

    @Test
    void saveWriteBook() {
//        when(authorService.saveWriteBook()).thenReturn();
    }

    @Test
    void updateAuthor() {
//        when(authorService.updateAuthor()).thenReturn();
    }

    @Test
    void deleteAuthor() {
//        when(authorService.deleteAuthor()).thenReturn();
    }
}