package exam.project.library.controller;

import exam.project.library.model.Author;
import exam.project.library.model.Write;
import exam.project.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthor(), HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<List<Author>> getAuthorById(@PathVariable("authorId") Long authorId) {
        return new ResponseEntity<>(authorService.getAuthorById(authorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> addAuthor(@Valid @RequestBody Author author) {
        authorService.saveNewAuthor(author);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/author/");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping("/write")
    @ResponseStatus(HttpStatus.CREATED)
    public void writeBook(@Valid @RequestBody Write write) {
        authorService.saveWriteBook(Long.parseLong(write.getAuthorId()), Long.parseLong(write.getBookId()));
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