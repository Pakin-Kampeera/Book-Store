package exam.project.library.services;

import exam.project.library.models.Publisher;

import java.util.UUID;

public interface PublisherService {
    Publisher getPublisherById(UUID publisherId);

    Publisher saveNewPublisher(Publisher publisherDto);

    void updatePublisher(UUID publisherId, Publisher publisherDto);

    void deletePublisher(UUID publisherId);
}
