package exam.project.library.services;

import exam.project.library.models.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublisher();

    Publisher getPublisherById(Long publisherId);

    Publisher saveNewPublisher(Long publisherDto);

    void updatePublisher(Long publisherId, Publisher publisherDto);

    void deletePublisher(Long publisherId);
}
