package exam.project.library.service.implementations;

import exam.project.library.model.Publisher;
import exam.project.library.repository.PublisherRepository;
import exam.project.library.service.PublisherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAllPublisher() {
        return null;
    }

    @Override
    public List<Publisher> getPublisherById(Long publisherId) {
        return null;
    }

    @Override
    public int saveNewPublisher(Publisher publisher) {
        return 1;
    }

    @Override
    public void updatePublisher(Long publisherId, Publisher publisher) {

    }

    @Override
    public void deletePublisher(Long publisherId) {

    }
}