package exam.project.library.controller;

import exam.project.library.model.Book;
import exam.project.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/book")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Book>> getBookById(@PathVariable("bookId") Long bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> addBook(@Valid @RequestBody Book book) {
        bookService.saveNewBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/book/");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("bookId") Long bookId, @Valid @RequestBody Book book) {
        bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }
}
