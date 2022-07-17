package exam.project.library.service;

import exam.project.library.model.Publisher;
import exam.project.library.repository.PublisherRepository;
import exam.project.library.service.implementations.PublisherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherServiceTest {

    @InjectMocks
    private PublisherServiceImpl publisherService;

    @Mock
    private PublisherRepository publisherRepository;

    private Publisher publisher1, publisher2;

    @BeforeEach
    void setUp() {
        this.publisher1 = new Publisher()
                .setPublisherId(1L)
                .setName("London House")
                .setStreet("1234")
                .setCity("Manchester")
                .setZip("2456");

        this.publisher2 = new Publisher()
                .setPublisherId(1L)
                .setName("City Hall")
                .setStreet("7432")
                .setCity("Houston")
                .setZip("8589");
    }

    @Test
    void getAllPublisher() {
        // given
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);
        publisherSet.add(publisher2);
        given(publisherRepository.getAllPublisher()).willReturn(publisherSet);

        // when
        List<Publisher> publishers = publisherService.getAllPublisher();

        // then
        assertEquals(2, publishers.size());
        verify(publisherRepository, times(1)).getAllPublisher();
    }

    @Test
    void getPublisherById() {
        // given
        List<Publisher> publisherSet = new ArrayList<>();
        publisherSet.add(publisher1);
        given(publisherRepository.getPublisherById(anyLong())).willReturn(publisherSet);

        // when
        List<Publisher> publishers = publisherService.getPublisherById(anyLong());

        // then
        assertEquals(1, publishers.size());
        verify(publisherRepository, times(1)).getPublisherById(anyLong());
    }

    @Test
    void saveNewPublisher() {
        // given
        given(publisherRepository.saveNewPublisher(any())).willReturn(1);

        // when
        publisherService.saveNewPublisher(any());

        // then
        assertEquals(1, publisherRepository.saveNewPublisher(publisher1));
    }

    @Test
    void updatePublisher() {
        // when
        publisherService.updatePublisher(anyLong(), any());

        // then
        verify(publisherRepository, times(1)).updatePublisher(anyLong(), any());
    }

    @Test
    void deletePublisher() {
        // when
        publisherService.deletePublisher(anyLong());

        // then
        verify(publisherRepository, times(1)).deletePublisher(anyLong());
    }
}