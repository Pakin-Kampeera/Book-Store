package exam.project.library.controllers;

import exam.project.library.models.Author;
import exam.project.library.services.AuthorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RequestMapping("/api/v1/author")
@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Author> getAllAuthors() {
        return new ResponseEntity(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("authorId") Long authorId) {
        return new ResponseEntity(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addAuthor(@Valid @RequestBody Author author) {
        authorService.saveNewAuthor(author);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/author/");

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PostMapping("/write")
    @ResponseStatus(HttpStatus.CREATED)
    public void writeBook(@Valid @RequestBody HashMap<String, String> body) {
        authorService.saveWriteBook(Long.parseLong(body.get("authorId")), Long.parseLong(body.get("bookId")));
    }

    @PutMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@PathVariable("authorId") Long authorId, @Valid @RequestBody Author author) {
        authorService.updateAuthor(authorId, author);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
