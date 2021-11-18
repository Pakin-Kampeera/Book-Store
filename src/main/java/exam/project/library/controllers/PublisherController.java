package exam.project.library.controllers;

import exam.project.library.models.Publisher;
import exam.project.library.services.PublisherService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/publisher")
@RestController
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public ResponseEntity<Publisher> getAllPublisher() {
        return new ResponseEntity(publisherService.getAllPublisher(), HttpStatus.OK);
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity getMemberById(@PathVariable("publisherId") Long publisherId) {
        return new ResponseEntity(publisherService.getPublisherById(publisherId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addPublisher(@Valid @RequestBody Publisher publisher) {
        publisherService.saveNewPublisher(publisher);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/v1/api/publisher");

        return new ResponseEntity(headers, HttpStatus.OK);
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
