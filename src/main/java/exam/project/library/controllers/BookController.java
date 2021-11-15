package exam.project.library.controllers;

import exam.project.library.models.BookDto;
import exam.project.library.services.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/book")
@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable("bookId") UUID bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody BookDto bookDto) {
        BookDto newBook = bookService.saveNewBook(bookDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/book/" + newBook.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("bookId") UUID bookId, @RequestBody BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("bookId") UUID bookId) {
        bookService.deleteBook(bookId);
    }
}
