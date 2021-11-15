package exam.project.library.services;

import exam.project.library.models.PublisherDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Override
    public PublisherDto getPublisherById(UUID publisherId) {
        return null;
    }

    @Override
    public PublisherDto saveNewPublisher(PublisherDto publisherDto) {
        return null;
    }

    @Override
    public void updatePublisher(UUID publisherId, PublisherDto publisherDto) {

    }

    @Override
    public void deletePublisher(UUID publisherId) {

    }
}
