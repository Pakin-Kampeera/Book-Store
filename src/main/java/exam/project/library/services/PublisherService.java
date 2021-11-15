package exam.project.library.services;

import exam.project.library.models.PublisherDto;

import java.util.UUID;

public interface PublisherService {
    PublisherDto getPublisherById(UUID publisherId);

    PublisherDto saveNewPublisher(PublisherDto publisherDto);

    void updatePublisher(UUID publisherId, PublisherDto publisherDto);

    void deletePublisher(UUID publisherId);
}
