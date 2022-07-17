package exam.project.library.repository;

import exam.project.library.model.Author;
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
class AuthorRepositoryTest {

    @InjectMocks
    private AuthorRepository authorRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private Author author1, author2;

    @BeforeEach
    void setUp() {
        this.author1 = new Author()
                .setAuthorId(1L)
                .setFirstName("Peter")
                .setLastName("Kim");

        this.author2 = new Author()
                .setAuthorId(2L)
                .setFirstName("Bob")
                .setLastName("Euro");
    }

    @Test
    void getAllAuthor() {
        // given
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);
        authorSet.add(author2);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class))).willReturn(authorSet);

        // when
        List<Author> authors = authorRepository.getAllAuthor();

        // then
        assertEquals(2, authors.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class));
    }

    @Test
    void getAuthorById() {
        // given
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);
        given(jdbcTemplate.query(anyString(), any(ResultSetExtractor.class), anyLong())).willReturn(authorSet);

        // when
        List<Author> authors = authorRepository.getAuthorById(1L);

        // then
        assertEquals(1, authors.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(ResultSetExtractor.class), anyLong());
    }

    @Test
    void saveNewAuthor() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyString())).willReturn(1);

        // when
        int savedAuthor = authorRepository.saveNewAuthor(author1);

        // then
        assertEquals(1, savedAuthor);
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString());
    }

    @Test
    void saveWriteBook() {
        // given
        given(jdbcTemplate.update(anyString(), anyLong(), anyLong())).willReturn(1);

        // when
        authorRepository.saveWriteBook(1L, 1L);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong(), anyLong());
    }

    @Test
    void updateAuthor() {
        // given
        given(jdbcTemplate.update(anyString(), anyString(), anyString(), anyLong())).willReturn(1);

        // when
        authorRepository.updateAuthor(1L, author1);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyLong());
    }

    @Test
    void deleteAuthor() {
        // given
        given(jdbcTemplate.update(anyString(), anyLong())).willReturn(1);

        // when
        authorRepository.deleteAuthor(1L);

        // then
        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
    }

}