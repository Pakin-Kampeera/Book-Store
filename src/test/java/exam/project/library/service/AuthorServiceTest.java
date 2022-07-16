package exam.project.library.service;

import exam.project.library.model.Author;
import exam.project.library.model.Book;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.service.implementations.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorRepository authorRepository;

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
        given(authorRepository.getAllAuthor()).willReturn(authorSet);

        List<Author> author = authorService.getAllAuthor();

        assertEquals(2, author.size());
        verify(authorRepository, times(1)).getAllAuthor();
    }

    @Test
    void getAuthorById() {
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);
        given(authorRepository.getAuthorById(anyLong())).willReturn(authorSet);

        List<Author> author = authorService.getAuthorById(1L);

        assertEquals(1, author.size());
        verify(authorRepository, times(1)).getAuthorById(anyLong());
    }

//    @Test
//    void saveNewAuthor() {
//        when(jdbcTemplate.update(anyString(), anyString(), anyString())).thenReturn(1);
//
//        assertEquals(1, authorRepository.saveNewAuthor(author1));
//
//        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString());
//    }
//
//    @Test
//    void saveWriteBook() {
//        when(jdbcTemplate.update(anyString(), anyLong(), anyLong())).thenReturn(1);
//
//        authorRepository.saveWriteBook(1L, 1L);
//
//        verify(jdbcTemplate, times(1)).update(anyString(), anyLong(), anyLong());
//    }
//
//    @Test
//    void updateAuthor() {
//        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyLong())).thenReturn(1);
//
//        authorRepository.updateAuthor(1L, author1);
//
//        verify(jdbcTemplate, times(1)).update(anyString(), anyString(), anyString(), anyLong());
//    }
//
//    @Test
//    void deleteAuthor() {
//        when(jdbcTemplate.update(anyString(), anyLong())).thenReturn(1);
//
//        authorRepository.deleteAuthor(1L);
//
//        verify(jdbcTemplate, times(1)).update(anyString(), anyLong());
//    }
}