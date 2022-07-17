package exam.project.library.service;

import exam.project.library.model.Author;
import exam.project.library.repository.AuthorRepository;
import exam.project.library.service.implementations.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

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
        given(authorRepository.getAllAuthor()).willReturn(authorSet);

        // when
        List<Author> author = authorService.getAllAuthor();

        // then
        assertEquals(2, author.size());
        verify(authorRepository, times(1)).getAllAuthor();
    }

    @Test
    void getAuthorById() {
        // given
        List<Author> authorSet = new ArrayList<>();
        authorSet.add(author1);
        given(authorRepository.getAuthorById(anyLong())).willReturn(authorSet);

        // when
        List<Author> author = authorService.getAuthorById(anyLong());

        // then
        assertEquals(1, author.size());
        verify(authorRepository, times(1)).getAuthorById(anyLong());
    }

    @Test
    void saveNewAuthor() {
        // given
        given(authorRepository.saveNewAuthor(any())).willReturn(1);

        // when
        authorService.saveNewAuthor(any());

        // then
        verify(authorRepository, times(1)).saveNewAuthor(any());
    }

    @Test
    void saveWriteBook() {
        // given
        given(authorRepository.saveWriteBook(anyLong(), anyLong())).willReturn(1);

        // when
        authorService.saveWriteBook(anyLong(), anyLong());

        // then
        verify(authorRepository, times(1)).saveWriteBook(anyLong(), anyLong());
    }

    @Test
    void updateAuthor() {
        // when
        authorService.updateAuthor(anyLong(), any());

        // then
        verify(authorRepository, times(1)).updateAuthor(anyLong(), any());
    }

    @Test
    void deleteAuthor() {
        // when
        authorService.deleteAuthor(anyLong());

        // then
        verify(authorRepository, times(1)).deleteAuthor(anyLong());
    }
}