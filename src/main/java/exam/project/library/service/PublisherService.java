package exam.project.library.service;

import exam.project.library.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getAllPublisher();

    List<Publisher> getPublisherById(Long publisherId);

    void saveNewPublisher(Publisher publisher);

    void updatePublisher(Long publisherId, Publisher publisher);

    void deletePublisher(Long publisherId);
}
