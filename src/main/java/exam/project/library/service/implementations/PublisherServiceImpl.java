package exam.project.library.service.implementations;

import exam.project.library.model.Publisher;
import exam.project.library.repository.PublisherRepository;
import exam.project.library.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    @Cacheable(value = "publishers")
    public List<Publisher> getAllPublisher() {
        return publisherRepository.getAllPublisher();
    }

    @Override
    @Cacheable(value = "publisher", key = "#publisherId", unless = "#result==null")
    public List<Publisher> getPublisherById(Long publisherId) {
        return publisherRepository.getPublisherById(publisherId);
    }

    @Override
    @CacheEvict(value = "publishers", allEntries = true)
    public void saveNewPublisher(Publisher publisher) {
        publisherRepository.saveNewPublisher(publisher);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "publishers", allEntries = true),
            @CacheEvict(value = "publisher", key = "#publisherId")
    })
    public void updatePublisher(Long publisherId, Publisher publisher) {
        publisherRepository.updatePublisher(publisherId, publisher);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "publishers", allEntries = true),
            @CacheEvict(value = "publisher", key = "#publisherId")
    })
    public void deletePublisher(Long publisherId) {
        publisherRepository.deletePublisher(publisherId);
    }
}
