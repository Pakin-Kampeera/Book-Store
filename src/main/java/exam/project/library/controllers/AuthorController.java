package exam.project.library.controllers;

import exam.project.library.models.Author;
import exam.project.library.services.AuthorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v1/api/author")
@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Author> getAllBooks() {
        return new ResponseEntity(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getBookById(@PathVariable("authorId") Long authorId) {
        return new ResponseEntity(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addBook(@Valid @RequestBody Author author) {
        authorService.saveNewAuthor(author);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/author/");

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("authorId") Long authorId, @Valid @RequestBody Author author) {
        authorService.updateAuthor(authorId, author);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
