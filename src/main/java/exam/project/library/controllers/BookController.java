package exam.project.library.controllers;

import exam.project.library.models.Book;
import exam.project.library.services.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/book")
@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Book> getAllBooks() {
        return new ResponseEntity(bookService.getAllBook(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
        return new ResponseEntity(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody Book book) {
        bookService.saveNewBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/book/");

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookService.updateBook(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }
}
