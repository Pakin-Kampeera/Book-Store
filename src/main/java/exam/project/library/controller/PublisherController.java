package exam.project.library.controller;

import exam.project.library.model.Publisher;
import exam.project.library.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<Publisher>> getAllPublisher() {
        return new ResponseEntity<>(publisherService.getAllPublisher(), HttpStatus.OK);
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<List<Publisher>> getMemberById(@PathVariable("publisherId") Long publisherId) {
        return new ResponseEntity<>(publisherService.getPublisherById(publisherId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> addPublisher(@Valid @RequestBody Publisher publisher) {
        publisherService.saveNewPublisher(publisher);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/v1/api/publisher");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@PathVariable("publisherId") Long publisherId, @Valid @RequestBody Publisher publisher) {
        publisherService.updatePublisher(publisherId, publisher);
    }

    @DeleteMapping("/{publisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable("publisherId") Long publisherId) {
        publisherService.deletePublisher(publisherId);
    }
}
