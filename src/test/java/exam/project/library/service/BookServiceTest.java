package exam.project.library.service;

import exam.project.library.model.Book;
import exam.project.library.repository.BookRepository;
import exam.project.library.service.implementations.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

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
        given(bookRepository.getAllBook()).willReturn(bookSet);

        // when
        List<Book> books = bookService.getAllBook();

        // then
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).getAllBook();
    }

    @Test
    void getBookById() {
        // given
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        given(bookRepository.getBookById(anyLong())).willReturn(bookSet);

        // when
        List<Book> books = bookService.getBookById(anyLong());

        // then
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).getBookById(anyLong());
    }

//    @Test
//    void saveNewBook() {
//        // given
//        given(bookRepository.saveNewBook(any(), anyLong())).willReturn(1L);
//
//        // when
//        bookService.saveNewBook(any());
//
//        // then
//        verify(bookRepository, times(1)).saveNewBook(any(), anyLong());
//    }
//
//    @Test
//    void updateBook() {
//        // when
//        bookService.updateBook(anyLong(), any());
//
//        // then
//        verify(bookRepository, times(1)).updateBook(anyLong(), any());
//    }

    @Test
    void deleteBook() {
        // when
        bookService.deleteBook(anyLong());

        // then
        verify(bookRepository, times(1)).deleteBook(anyLong());
    }
}